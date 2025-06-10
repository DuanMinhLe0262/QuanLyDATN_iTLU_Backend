package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.GiangVienResponse;
import com.example.backend_itlu.service.GiangVienService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/giangvien")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GiangVienController {
    GiangVienService giangVienService;

    @PostMapping
    public ApiResponse<GiangVienResponse> createGiangVien(@RequestBody @Valid GiangVienCreationRequest request) {
        return ApiResponse.<GiangVienResponse>builder()
                .result(giangVienService.createGiangVien(request))
                .build();
    }

    @PutMapping("/{giangVienId}")
    public ApiResponse<GiangVienResponse> updateGiangVien(@PathVariable("giangVienId") String giangVienId, @RequestBody @Valid GiangVienUpdateRequest request) {
        return ApiResponse.<GiangVienResponse>builder()
                .result(giangVienService.updateGiangVien(giangVienId, request))
                .build();
    }

    @GetMapping("/{giangVienId}")
    public ApiResponse<GiangVienResponse> getGiangVienById(@PathVariable("giangVienId") String giangVienId) {
        return ApiResponse.<GiangVienResponse>builder()
                .result(giangVienService.getGiangVienById(giangVienId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<GiangVienResponse>> getAllGiangVien() {
        return ApiResponse.<List<GiangVienResponse>>builder()
                .result(giangVienService.getAllGiangVien())
                .build();

    }

    @DeleteMapping("/{giangVienId}")
    public void deleteGiangVienById(@PathVariable("giangVienId") String giangVienId) {
        giangVienService.deleteGiangVien(giangVienId);
    }

}
