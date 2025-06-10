package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.SinhVienResponse;
import com.example.backend_itlu.dto.response.UserResponse;
import com.example.backend_itlu.service.SinhVienService;
import com.example.backend_itlu.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sinhvien")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SinhVienController {
    SinhVienService sinhVienService;

    @PostMapping
    public ApiResponse<SinhVienResponse> createSinhVien(@RequestBody @Valid SinhVienCreationRequest request) {
        return ApiResponse.<SinhVienResponse>builder()
                .result(sinhVienService.createSinhVien(request))
                .build();
    }

    @PutMapping("/{sinhVienId}")
    public ApiResponse<SinhVienResponse> updateSinhVien(@PathVariable("sinhVienId") String sinhVienId, @RequestBody @Valid SinhVienUpdateRequest request) {
        return ApiResponse.<SinhVienResponse>builder()
                .result(sinhVienService.updateSinhVien(sinhVienId, request))
                .build();
    }

    @GetMapping("/{sinhVienId}")
    public ApiResponse<SinhVienResponse> getSinhVienById(@PathVariable("sinhVienId") String sinhVienId) {
        return ApiResponse.<SinhVienResponse>builder()
                .result(sinhVienService.getSinhVienById(sinhVienId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<SinhVienResponse>> getAllSinhVien() {
        return ApiResponse.<List<SinhVienResponse>>builder()
                .result(sinhVienService.getAllSinhVien())
                .build();

    }

    @DeleteMapping("/{sinhVienId}")
    public void deleteSinhVienById(@PathVariable("sinhVienId") String sinhVienId) {
        sinhVienService.deleteSinhVien(sinhVienId);
    }

}
