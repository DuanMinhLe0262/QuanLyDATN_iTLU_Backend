package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.LopCreationRequest;
import com.example.backend_itlu.dto.request.LopUpdateRequest;
import com.example.backend_itlu.dto.request.NganhCreationRequest;
import com.example.backend_itlu.dto.request.NganhUpdateRequest;
import com.example.backend_itlu.dto.response.LopResponse;
import com.example.backend_itlu.dto.response.NganhResponse;
import com.example.backend_itlu.entity.Lop;
import com.example.backend_itlu.entity.Nganh;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.LopMapper;
import com.example.backend_itlu.repository.LopRepository;
import com.example.backend_itlu.repository.NganhRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LopService {

    LopRepository lopRepo;
    LopMapper lopMapper;
    private final NganhRepository nganhRepository;

    public LopResponse createLop(LopCreationRequest request) {
        Nganh nganh = nganhRepository.findById(request.getNganhId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Lop lop = lopMapper.toLop(request);
        lop.setNganh(nganh);
        return lopMapper.toLopResponse(lopRepo.save(lop));
    }

    public LopResponse updateLop(String lopId, LopUpdateRequest request) {

        Lop lop = lopRepo.findById(lopId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        lopMapper.updateLopFromRequest(lop, request);
        return lopMapper.toLopResponse(lopRepo.save(lop));
    }

    public LopResponse getLopById(String lopId) {

        Lop lop = lopRepo.findById(lopId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return lopMapper.toLopResponse(lop);

    }

    public List<LopResponse> getAllLop() {

        List<Lop> lopList = lopRepo.findAll();
        return lopMapper.toLopResponseList(lopList);
    }

    public void deleteLop(String lopId){
        if(!lopRepo.existsById(lopId))
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        lopRepo.deleteById(lopId);
    }
}
