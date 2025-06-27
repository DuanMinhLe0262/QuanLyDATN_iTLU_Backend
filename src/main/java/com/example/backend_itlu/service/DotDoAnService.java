package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.DotDoAnCreationRequest;
import com.example.backend_itlu.dto.request.DotDoAnUpdateRequest;
import com.example.backend_itlu.dto.response.DotDoAnResponse;
import com.example.backend_itlu.entity.DotDoAn;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.DotDoAnMapper;
import com.example.backend_itlu.repository.DotDoAnRepository;
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
public class DotDoAnService {

    DotDoAnRepository dotDoAnRepo;
    DotDoAnMapper dotDoAnMapper;

    public DotDoAnResponse createDotDoAn(DotDoAnCreationRequest request) {

        DotDoAn dotDoAn = dotDoAnMapper.toDotDoAn(request);
        return dotDoAnMapper.toDotDoAnResponse(dotDoAnRepo.save(dotDoAn));
    }


    public DotDoAnResponse updateDotDoAn(String dotDoAnId, DotDoAnUpdateRequest request) {
        DotDoAn dotDoAn = dotDoAnRepo.findById(dotDoAnId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        dotDoAnMapper.updateDotDoAnFromRequest(dotDoAn, request);
        return dotDoAnMapper.toDotDoAnResponse(dotDoAnRepo.save(dotDoAn));
    }

    public void deleteDotDoAn(String dotDoAnId) {
        boolean exists = dotDoAnRepo.existsById(dotDoAnId);
        if (!exists) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        dotDoAnRepo.deleteById(dotDoAnId);
    }


    public List<DotDoAnResponse> getAllDotDoAn() {
        List<DotDoAn> dotDoAnList = dotDoAnRepo.findAll();
        return dotDoAnMapper.toDotDoAnResponseList(dotDoAnList);
    }

    public DotDoAnResponse getDotDoAnById(String dotDoAnId) {
        DotDoAn dotDoAn = dotDoAnRepo.findById(dotDoAnId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return dotDoAnMapper.toDotDoAnResponse(dotDoAn);
    }
}
