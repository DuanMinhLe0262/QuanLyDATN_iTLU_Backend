package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.ApiResponse;
import com.example.backend_itlu.dto.request.KhoaCreationRequest;
import com.example.backend_itlu.dto.request.KhoaUpdateRequest;
import com.example.backend_itlu.dto.response.KhoaResponse;
import com.example.backend_itlu.service.KhoaService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khoa")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhoaController {

    KhoaService khoaService;

    @PostMapping
    public ApiResponse<KhoaResponse> createKhoa(@RequestBody @Valid KhoaCreationRequest request) {
        return ApiResponse.<KhoaResponse>builder()
                .result(khoaService.createKhoa(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<KhoaResponse>> getAllKhoa() {

        return ApiResponse.<List<KhoaResponse>>builder()
                .result(khoaService.getAllKhoa())
                .build();
    }

    @GetMapping("/{khoaId}")
    public ApiResponse<KhoaResponse> getKhoa(@PathVariable("khoaId") String khoaId) {
        return ApiResponse.<KhoaResponse>builder()
                .result(khoaService.getKhoaById(khoaId))
                .build();
    }

    @PutMapping("/{khoaId}")
    public ApiResponse<KhoaResponse> updateKhoa(@PathVariable String khoaId, @RequestBody @Valid KhoaUpdateRequest request) {
        return ApiResponse.<KhoaResponse>builder()
                .result(khoaService.updateKhoa(khoaId, request))
                .build();
    }

    @DeleteMapping("/{khoaId}")
    public void deleteKhoa(@PathVariable("khoaId") String khoaId) {
        khoaService.deleteKhoa(khoaId);
    }
}

