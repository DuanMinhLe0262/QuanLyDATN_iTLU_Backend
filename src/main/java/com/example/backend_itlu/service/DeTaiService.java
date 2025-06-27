package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.DeTaiCreationRequest;
import com.example.backend_itlu.dto.request.DeTaiGoiYCreationRequest;
import com.example.backend_itlu.dto.request.DeTaiGoiYUpdateRequest;
import com.example.backend_itlu.dto.request.DeTaiUpdateRequest;
import com.example.backend_itlu.dto.response.DeTaiGoiYResponse;
import com.example.backend_itlu.dto.response.DeTaiResponse;
import com.example.backend_itlu.dto.response.ImportResult;
import com.example.backend_itlu.entity.*;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.DeTaiGoiYMapper;
import com.example.backend_itlu.mapper.DeTaiMapper;
import com.example.backend_itlu.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeTaiService {


    DeTaiRepository deTaiRepo;
    DeTaiMapper deTaiMapper;
    SinhVienRepository sinhVienRepo;
    GiangVienRepository giangVienRepo;

    public DeTaiResponse createDeTai(DeTaiCreationRequest request){

        SinhVien sinhVien = sinhVienRepo.findByMaSinhVien(request.getMaSinhVien())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (deTaiRepo.existsDeTaiBySinhVienId(sinhVien.getId())) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        GiangVien giangVien = giangVienRepo.findByMaGiangVien(request.getMaGiangVien())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        DeTai deTai = DeTai.builder()
                .tenDeTai(request.getTenDeTai())
                .moTa(request.getMoTa())
                .trangThai(TrangThaiNopBai.CHO_DUYET)
                .sinhVien(sinhVien)
                .giangVien(giangVien)
                .build();

        return deTaiMapper.toDeTaiResponse(deTaiRepo.save(deTai));
    }

    public DeTaiResponse updateDeTai(String deTaiId, DeTaiUpdateRequest request){

        DeTai deTai = deTaiRepo.findById(deTaiId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (deTai.getTrangThai() != TrangThaiNopBai.CHO_DUYET) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        deTaiMapper.updateDeTaiFromRequest(deTai, request);
        return deTaiMapper.toDeTaiResponse(deTaiRepo.save(deTai));
    }

    public DeTaiResponse getDeTaiById(String deTaiId){
        DeTai deTai = deTaiRepo.findById(deTaiId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return deTaiMapper.toDeTaiResponse(deTai);
    }

    public List<DeTaiResponse> getAllDeTai(){
        List<DeTai> deTaiList = deTaiRepo.findAll();
        return deTaiMapper.toDeTaiResponseList(deTaiList);
    }

    public void deleteDeTai(String deTaiId){
        if(!deTaiRepo.existsById(deTaiId))
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        deTaiRepo.deleteById(deTaiId);
    }

//    public ImportResult<DeTaiResponse> importFromExcel(MultipartFile file) {
//    }
}
