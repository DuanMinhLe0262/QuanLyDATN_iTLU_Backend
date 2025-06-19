package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.ApiResponse;
import com.example.backend_itlu.dto.request.DotDoAnCreationRequest;
import com.example.backend_itlu.dto.request.DotDoAnUpdateRequest;
import com.example.backend_itlu.dto.response.DotDoAnResponse;
import com.example.backend_itlu.service.DotDoAnService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dotdoan")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DotDoAnController {

    DotDoAnService dotDoAnService;

    @PostMapping
    public ApiResponse<DotDoAnResponse> createDotDoAn(@RequestBody @Valid DotDoAnCreationRequest request) {
        return ApiResponse.<DotDoAnResponse>builder()
                .result(dotDoAnService.createDotDoAn(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<DotDoAnResponse>> getAllDotDoAn() {

        return ApiResponse.<List<DotDoAnResponse>>builder()
                .result(dotDoAnService.getAllDotDoAn())
                .build();
    }

    @GetMapping("/{dotDoAnId}")
    public ApiResponse<DotDoAnResponse> getDotDoAn(@PathVariable("dotDoAnId") String dotDoAnId) {
        return ApiResponse.<DotDoAnResponse>builder()
                .result(dotDoAnService.getDotDoAnById(dotDoAnId))
                .build();
    }

    @PutMapping("/{dotDoAnId}")
    public ApiResponse<DotDoAnResponse> updateDotDoAn(@PathVariable String dotDoAnId, @RequestBody @Valid DotDoAnUpdateRequest request) {
        return ApiResponse.<DotDoAnResponse>builder()
                .result(dotDoAnService.updateDotDoAn(dotDoAnId, request))
                .build();
    }

    @DeleteMapping("/{dotDoAnId}")
    public void deleteDotDoAn(@PathVariable("dotDoAnId") String dotDoAnId) {
        dotDoAnService.deleteDotDoAn(dotDoAnId);
    }
}

