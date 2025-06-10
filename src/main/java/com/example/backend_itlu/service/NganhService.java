package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.NganhCreationRequest;
import com.example.backend_itlu.dto.request.NganhUpdateRequest;
import com.example.backend_itlu.dto.response.NganhResponse;
import com.example.backend_itlu.entity.BoMon;
import com.example.backend_itlu.entity.Nganh;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.NganhMapper;
import com.example.backend_itlu.repository.BoMonRepository;
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
public class NganhService {


    NganhRepository nganhRepo;
    NganhMapper nganhMapper;
    BoMonRepository boMonRepository;

    public NganhResponse createNganh(NganhCreationRequest request){
        BoMon boMon = boMonRepository.findById(request.getBoMonId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Nganh nganh = nganhMapper.toNganh(request);
        nganh.setBoMon(boMon);

        return nganhMapper.toNganhResponse(nganhRepo.save(nganh));
    }

    public NganhResponse updateNganh(String nganhId, NganhUpdateRequest request){
        Nganh nganh = nganhRepo.findById(nganhId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        nganhMapper.updateNganhFromRequest(nganh, request);
        return nganhMapper.toNganhResponse(nganhRepo.save(nganh));
    }

    public NganhResponse getNganhById(String nganhId){
        Nganh nganh = nganhRepo.findById(nganhId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return nganhMapper.toNganhResponse(nganh);
    }

    public List<NganhResponse> getAllNganh(){
        List<Nganh> nganhList = nganhRepo.findAll();
        return nganhMapper.toNganhResponseList(nganhList);
    }

    public void deleteNganh(String nganhId){
        if(!nganhRepo.existsById(nganhId))
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        nganhRepo.deleteById(nganhId);
    }
}
