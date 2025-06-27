package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.DeTaiResponse;
import com.example.backend_itlu.dto.response.ImportResult;
import com.example.backend_itlu.dto.response.SinhVienHuongDanResponse;
import com.example.backend_itlu.service.DeTaiService;
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
@RequestMapping("/detai")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DeTaiController {
    DeTaiService deTaiService;

    @PostMapping
    public ApiResponse<DeTaiResponse> createDeTai(@RequestBody @Valid DeTaiCreationRequest request) {
        return ApiResponse.<DeTaiResponse>builder()
                .result(deTaiService.createDeTai(request))
                .build();
    }

//    @PostMapping("/uploadExcel")
//    public ApiResponse<ImportResult<DeTaiResponse>> importSinhVien(@RequestParam("file") MultipartFile file) {
//        ImportResult<DeTaiResponse> result = deTaiService.importFromExcel(file);
//        return ApiResponse.<ImportResult<DeTaiResponse>>builder()
//                .result(result)
//                .build();
//    }

    @PutMapping("/{deTaiId}")
    public ApiResponse<DeTaiResponse> updateDeTai(@PathVariable("deTaiId") String deTaiId, @RequestBody @Valid DeTaiUpdateRequest request) {
        return ApiResponse.<DeTaiResponse>builder()
                .result(deTaiService.updateDeTai(deTaiId, request))
                .build();
    }

    @GetMapping("/{deTaiId}")
    public ApiResponse<DeTaiResponse> getDeTaiById(@PathVariable("deTaiId") String deTaiId) {
        return ApiResponse.<DeTaiResponse>builder()
                .result(deTaiService.getDeTaiById(deTaiId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<DeTaiResponse>> getAllDeTai() {
        return ApiResponse.<List<DeTaiResponse>>builder()
                .result(deTaiService.getAllDeTai())
                .build();

    }

    @DeleteMapping("/{deTaiId}")
    public void deleteSinhVienById(@PathVariable("deTaiId") String deTaiId) {
        deTaiService.deleteDeTai(deTaiId);
    }

}
