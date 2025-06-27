package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.ImportResult;
import com.example.backend_itlu.dto.response.SinhVienHuongDanResponse;
import com.example.backend_itlu.service.SinhVienHuongDanService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sinhvienhuongdan")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SinhVienHuongDanController {
    SinhVienHuongDanService sinhVienHuongDanService;

    @PostMapping
    public ApiResponse<SinhVienHuongDanResponse> createSinhVienHuongDan(@RequestBody @Valid SinhVienHuongDanCreationRequest request) {
        return ApiResponse.<SinhVienHuongDanResponse>builder()
                .result(sinhVienHuongDanService.createSinhVienHuongDan(request))
                .build();
    }

    @PostMapping("/uploadExcel")
    public ApiResponse<ImportResult<SinhVienHuongDanResponse>> importSinhVien(@RequestParam("file") MultipartFile file) {
        ImportResult<SinhVienHuongDanResponse> result = sinhVienHuongDanService.importFromExcel(file);
        return ApiResponse.<ImportResult<SinhVienHuongDanResponse>>builder()
                .result(result)
                .build();
    }

    @PutMapping("/{sinhVienHuongDanId}")
    public ApiResponse<SinhVienHuongDanResponse> updateSinhVienHuongDan(@PathVariable("sinhVienHuongDanId") String sinhVienHuongDanId, @RequestBody @Valid SinhVienHuongDanUpdateRequest request) {
        return ApiResponse.<SinhVienHuongDanResponse>builder()
                .result(sinhVienHuongDanService.updateSinhVienHuongDan(sinhVienHuongDanId, request))
                .build();
    }

    @GetMapping("/{sinhVienHuongDanId}")
    public ApiResponse<SinhVienHuongDanResponse> getSinhVienHuongDanById(@PathVariable("sinhVienHuongDanId") String sinhVienHuongDanId) {
        return ApiResponse.<SinhVienHuongDanResponse>builder()
                .result(sinhVienHuongDanService.getSinhVienHuongDanById(sinhVienHuongDanId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<SinhVienHuongDanResponse>> getAllSinhVienHuongDan() {
        return ApiResponse.<List<SinhVienHuongDanResponse>>builder()
                .result(sinhVienHuongDanService.getAllSinhVienHuongDan())
                .build();

    }

    @DeleteMapping("/{sinhVienHuongDanId}")
    public void deleteSinhVienById(@PathVariable("sinhVienHuongDanId") String sinhVienHuongDanId) {
        sinhVienHuongDanService.deleteSinhVienHuongDan(sinhVienHuongDanId);
    }

}
