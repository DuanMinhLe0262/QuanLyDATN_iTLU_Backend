package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.TaiLieuCreationRequest;
import com.example.backend_itlu.dto.request.TaiLieuUpdateRequest;
import com.example.backend_itlu.dto.response.TaiLieuResponse;
import com.example.backend_itlu.entity.GiangVien;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.entity.TaiLieu;
import com.example.backend_itlu.entity.User;
import com.example.backend_itlu.enums.TrangThaiNopBai;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.TaiLieuMapper;
import com.example.backend_itlu.repository.GiangVienRepository;
import com.example.backend_itlu.repository.SinhVienRepository;
import com.example.backend_itlu.repository.TaiLieuRepository;
import com.example.backend_itlu.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaiLieuService {

    GiangVienRepository giangVienRepo;
    SinhVienRepository sinhVienRepo;
    TaiLieuRepository taiLieuRepo;
    TaiLieuMapper taiLieuMapper;
    UserRepository userRepo;

    public TaiLieuResponse createTaiLieu(TaiLieuCreationRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        SinhVien sinhVien = sinhVienRepo.findByUserId(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        TaiLieu taiLieu = TaiLieu.builder()
                .tenDeTai(request.getTenDeTai())
                .moTa(request.getMoTa())
                .sinhVien(sinhVien)
                .loai(request.getLoai())
                .fileUrl(request.getFileUrl())
                .ngayNop(LocalDateTime.now())
                .trangThai(TrangThaiNopBai.CHO_DUYET)
                .nhanXet("")
                .lanNop(request.getLanNop())
                .build();

        return taiLieuMapper.toTaiLieuResponse(taiLieuRepo.save(taiLieu));
    }

    public TaiLieuResponse updateTrangThaiTaiLieu(String id, TaiLieuUpdateRequest request) {
        TaiLieu taiLieu = taiLieuRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND, "Tài liệu không tồn tại"));

        GiangVien gv = giangVienRepo.findById(request.getGiangVienId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        taiLieu.setTrangThai(request.getTrangThai());
        taiLieu.setNhanXet(request.getNhanXet());
        taiLieu.setGiangVien(gv);

        return taiLieuMapper.toTaiLieuResponse(taiLieuRepo.save(taiLieu));
    }

    public TaiLieuResponse getTaiLieuById(String id) {
        TaiLieu taiLieu = taiLieuRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND, "Tài liệu không tồn tại"));

        return taiLieuMapper.toTaiLieuResponse(taiLieu);
    }

    public List<TaiLieuResponse> getTaiLieuBySinhVienId(String sinhVienId) {
        return taiLieuMapper.toTaiLieuResponseList(
                taiLieuRepo.findBySinhVienId(sinhVienId)
        );
    }

    public List<TaiLieuResponse> getAllTaiLieu() {
        return taiLieuMapper.toTaiLieuResponseList(taiLieuRepo.findAll());
    }
}
