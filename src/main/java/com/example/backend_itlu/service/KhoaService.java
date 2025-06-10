package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.KhoaCreationRequest;
import com.example.backend_itlu.dto.request.KhoaUpdateRequest;
import com.example.backend_itlu.dto.response.KhoaResponse;
import com.example.backend_itlu.entity.Khoa;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.KhoaMapper;
import com.example.backend_itlu.repository.KhoaRepository;
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
public class KhoaService {

    KhoaRepository khoaRepo;
    KhoaMapper khoaMapper;

    public KhoaResponse createKhoa(KhoaCreationRequest request) {
        if (khoaRepo.existsByTenKhoa(request.getTenKhoa())) {
            throw new AppException(ErrorCode.ENTITY_ALREADY_EXISTS);
        }

        Khoa khoa = khoaMapper.toKhoa(request);
        return khoaMapper.toKhoaResponse(khoaRepo.save(khoa));
    }


    public KhoaResponse updateKhoa(String khoaId, KhoaUpdateRequest request) {
        Khoa khoa = khoaRepo.findById(khoaId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        khoaMapper.updateKhoaFromRequest(khoa, request);
        return khoaMapper.toKhoaResponse(khoaRepo.save(khoa));
    }

    public void deleteKhoa(String khoaId) {
        boolean exists = khoaRepo.existsById(khoaId);
        if (!exists) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        khoaRepo.deleteById(khoaId);
    }


    public List<KhoaResponse> getAllKhoa() {
        List<Khoa> khoaList = khoaRepo.findAll();
        return khoaMapper.toKhoaResponseList(khoaList);
    }

    public KhoaResponse getKhoaById(String KhoaId) {
        Khoa Khoa = khoaRepo.findById(KhoaId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return khoaMapper.toKhoaResponse(Khoa);
    }
}
