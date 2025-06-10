package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.BoMonCreationRequest;
import com.example.backend_itlu.dto.request.BoMonUpdateRequest;
import com.example.backend_itlu.dto.request.KhoaCreationRequest;
import com.example.backend_itlu.dto.request.KhoaUpdateRequest;
import com.example.backend_itlu.dto.response.BoMonResponse;
import com.example.backend_itlu.dto.response.KhoaResponse;
import com.example.backend_itlu.entity.BoMon;
import com.example.backend_itlu.entity.Khoa;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.BoMonMapper;
import com.example.backend_itlu.mapper.KhoaMapper;
import com.example.backend_itlu.repository.BoMonRepository;
import com.example.backend_itlu.repository.KhoaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoMonService {


    BoMonRepository boMonRepo;
    BoMonMapper boMonMapper;
    KhoaRepository khoaRepo;

    public BoMonResponse createBoMon(BoMonCreationRequest request){

        Khoa khoa = khoaRepo.findById(request.getKhoaId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BoMon boMon = boMonMapper.toBoMon(request);
        boMon.setKhoa(khoa);
        return boMonMapper.toBoMonResponse(boMonRepo.save(boMon));
    }

    public BoMonResponse updateBoMon(String boMonId,BoMonUpdateRequest request){
        BoMon boMon = boMonRepo.findById(boMonId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boMonMapper.updateBoMonFromRequest(boMon, request);
        return boMonMapper.toBoMonResponse(boMonRepo.save(boMon));
    }

    public void deleteBoMon(String boMonId){
        if(!boMonRepo.existsById(boMonId)){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        boMonRepo.deleteById(boMonId);
    }

    public BoMonResponse getBoMonById(String boMonId){
        BoMon boMon = boMonRepo.findById(boMonId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return boMonMapper.toBoMonResponse(boMon);
    }

    public List<BoMonResponse> getAllBoMons(){
        List<BoMon> boMons = boMonRepo.findAll();

        return boMonMapper.toBoMonResponseList(boMons);
    }

}
