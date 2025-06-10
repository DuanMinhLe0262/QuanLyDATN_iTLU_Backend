package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.DeTaiGoiYCreationRequest;
import com.example.backend_itlu.dto.request.DeTaiGoiYUpdateRequest;
import com.example.backend_itlu.dto.response.DeTaiGoiYResponse;
import com.example.backend_itlu.entity.BoMon;
import com.example.backend_itlu.entity.DeTaiGoiY;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.DeTaiGoiYMapper;
import com.example.backend_itlu.repository.BoMonRepository;
import com.example.backend_itlu.repository.DeTaiGoiYRepository;
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
public class DeTaiGoiYService {


    DeTaiGoiYRepository DeTaiGoiYRepo;
    DeTaiGoiYMapper DeTaiGoiYMapper;
    BoMonRepository boMonRepository;

    public DeTaiGoiYResponse createDeTaiGoiY(DeTaiGoiYCreationRequest request){
        BoMon boMon = boMonRepository.findById(request.getBoMonId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        DeTaiGoiY DeTaiGoiY = DeTaiGoiYMapper.toDeTaiGoiY(request);
        DeTaiGoiY.setBomon(boMon);

        return DeTaiGoiYMapper.toDeTaiGoiYResponse(DeTaiGoiYRepo.save(DeTaiGoiY));
    }

    public DeTaiGoiYResponse updateDeTaiGoiY(String DeTaiGoiYId, DeTaiGoiYUpdateRequest request){
        DeTaiGoiY DeTaiGoiY = DeTaiGoiYRepo.findById(DeTaiGoiYId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        DeTaiGoiYMapper.updateDeTaiGoiYFromRequest(DeTaiGoiY, request);
        return DeTaiGoiYMapper.toDeTaiGoiYResponse(DeTaiGoiYRepo.save(DeTaiGoiY));
    }

    public DeTaiGoiYResponse getDeTaiGoiYById(String DeTaiGoiYId){
        DeTaiGoiY DeTaiGoiY = DeTaiGoiYRepo.findById(DeTaiGoiYId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return DeTaiGoiYMapper.toDeTaiGoiYResponse(DeTaiGoiY);
    }

    public List<DeTaiGoiYResponse> getAllDeTaiGoiY(){
        List<DeTaiGoiY> DeTaiGoiYList = DeTaiGoiYRepo.findAll();
        return DeTaiGoiYMapper.toDeTaiGoiYResponseList(DeTaiGoiYList);
    }

    public void deleteDeTaiGoiY(String DeTaiGoiYId){
        if(!DeTaiGoiYRepo.existsById(DeTaiGoiYId))
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        DeTaiGoiYRepo.deleteById(DeTaiGoiYId);
    }
}
