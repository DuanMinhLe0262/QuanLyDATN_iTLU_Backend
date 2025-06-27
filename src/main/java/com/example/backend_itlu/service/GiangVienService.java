package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.GiangVienCreationRequest;
import com.example.backend_itlu.dto.request.GiangVienUpdateRequest;
import com.example.backend_itlu.dto.request.SinhVienCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienUpdateRequest;
import com.example.backend_itlu.dto.response.GiangVienResponse;
import com.example.backend_itlu.dto.response.SinhVienResponse;
import com.example.backend_itlu.entity.*;
import com.example.backend_itlu.enums.Role;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.GiangVienMapper;
import com.example.backend_itlu.mapper.SinhVienMapper;
import com.example.backend_itlu.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GiangVienService {

    BoMonRepository boMonRepo;
    UserRepository userRepo;
    GiangVienRepository giangVienRepo;
    GiangVienMapper giangVienMapper;
    private final PasswordEncoder passwordEncoder;

    public GiangVienResponse createGiangVien(GiangVienCreationRequest request) {
        BoMon boMon = boMonRepo.findById(request.getBoMonId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode("Tlu@12345"))
                .roles(Set.of(Role.GIANGVIEN))
                .build();

        user = userRepo.save(user);

        GiangVien giangVien = giangVienMapper.toGiangVien(request);
        giangVien.setBoMon(boMon);
        giangVien.setUser(user);

        return giangVienMapper.toGiangVienResponse(giangVienRepo.save(giangVien));
    }

    public GiangVienResponse updateGiangVien(String giangVienId, GiangVienUpdateRequest request) {

        GiangVien giangVien = giangVienRepo.findById(giangVienId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        giangVienMapper.updateGiangVienFromRequest(giangVien, request);

        return giangVienMapper.toGiangVienResponse(giangVienRepo.save(giangVien));
    }

    public GiangVienResponse getGiangVienById(String giangVienId) {

        GiangVien giangVien = giangVienRepo.findById(giangVienId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return giangVienMapper.toGiangVienResponse(giangVien);
    }


    public List<GiangVienResponse> getAllGiangVien() {

        List<GiangVien> giangVienList = giangVienRepo.findAll();
        return giangVienMapper.toGiangVienResponseList(giangVienList);
    }

    public void deleteGiangVien(String giangVienId) {
        if(!giangVienRepo.existsById(giangVienId))
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        giangVienRepo.deleteById(giangVienId);
    }
}
