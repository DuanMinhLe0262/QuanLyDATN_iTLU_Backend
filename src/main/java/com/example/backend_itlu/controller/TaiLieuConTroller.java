package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.ApiResponse;
import com.example.backend_itlu.dto.request.TaiLieuCreationRequest;
import com.example.backend_itlu.dto.request.TaiLieuUpdateRequest;
import com.example.backend_itlu.dto.response.LopResponse;
import com.example.backend_itlu.dto.response.TaiLieuResponse;
import com.example.backend_itlu.service.TaiLieuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tailieu")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaiLieuConTroller {

    TaiLieuService taiLieuService;

    @PostMapping
    public ApiResponse<TaiLieuResponse> createTaiLieu(@RequestBody TaiLieuCreationRequest request) {
        TaiLieuResponse response = taiLieuService.createTaiLieu(request);
        return ApiResponse.<TaiLieuResponse>builder()
                .result(response)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TaiLieuResponse> updateTaiLieu(@PathVariable String id,
                                                      @RequestBody TaiLieuUpdateRequest request) {
        TaiLieuResponse response = taiLieuService.updateTrangThaiTaiLieu(id, request);
        return ApiResponse.<TaiLieuResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping("/sinhvien/{id}")
    public ApiResponse<List<TaiLieuResponse>> getBySinhVien(@PathVariable("id") String sinhVienId) {
        List<TaiLieuResponse> response = taiLieuService.getTaiLieuBySinhVienId(sinhVienId);
        return ApiResponse.<List<TaiLieuResponse>>builder()
                .result(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TaiLieuResponse> getById(@PathVariable String id) {
        TaiLieuResponse response = taiLieuService.getTaiLieuById(id);
        return ApiResponse.<TaiLieuResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<TaiLieuResponse>> getAllTaiLieu() {
        return ApiResponse.<List<TaiLieuResponse>>builder()
                .result(taiLieuService.getAllTaiLieu())
                .build();
    }


}
