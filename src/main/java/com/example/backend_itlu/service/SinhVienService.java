package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.SinhVienCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienUpdateRequest;
import com.example.backend_itlu.dto.response.SinhVienResponse;
import com.example.backend_itlu.entity.Lop;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.entity.User;
import com.example.backend_itlu.enums.Role;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.SinhVienMapper;
import com.example.backend_itlu.repository.LopRepository;
import com.example.backend_itlu.repository.SinhVienRepository;
import com.example.backend_itlu.repository.UserRepository;
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
public class SinhVienService {

    LopRepository lopRepository;
    SinhVienMapper sinhVienMapper;
    SinhVienRepository sinhVienRepo;
    UserRepository userRepository;
    UserService userService;

    private final PasswordEncoder passwordEncoder;


    public SinhVienResponse createSinhVien(SinhVienCreationRequest request) {

        Lop lop = lopRepository.findById(request.getLopId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode("Tlu@12345"))
                .roles(Set.of(Role.STUDENT))
                .build();

        user = userRepository.save(user);

        SinhVien sinhVien = sinhVienMapper.toSinhVien(request);
        sinhVien.setLop(lop);
        sinhVien.setUser(user);
        return sinhVienMapper.toSinhVienResponse(sinhVienRepo.save(sinhVien));
    }

    public SinhVienResponse updateSinhVien(String sinhVienId, SinhVienUpdateRequest request) {

        SinhVien sinhVien = sinhVienRepo.findById(sinhVienId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        sinhVienMapper.updateSinhVienFromRequest(sinhVien, request);
        return sinhVienMapper.toSinhVienResponse(sinhVienRepo.save(sinhVien));
    }

    public SinhVienResponse getSinhVienById(String sinhVienId) {

        SinhVien sinhVien = sinhVienRepo.findById(sinhVienId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return sinhVienMapper.toSinhVienResponse(sinhVien);
    }

    public List<SinhVienResponse> getAllSinhVien() {

        List<SinhVien> sinhVienList = sinhVienRepo.findAll();

        return sinhVienMapper.toSinhVienResponseList(sinhVienList);
    }

    public void deleteSinhVien(String sinhVienId) {
        if(!sinhVienRepo.existsById(sinhVienId))
            throw new AppException(ErrorCode.USER_NOT_FOUND);

        sinhVienRepo.deleteById(sinhVienId);
    }

}
