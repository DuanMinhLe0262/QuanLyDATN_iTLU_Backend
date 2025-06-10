package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.NganhResponse;
import com.example.backend_itlu.service.NganhService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nganh")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NganhController {

    NganhService nganhService;
    @PostMapping
    public ApiResponse<NganhResponse> createNganh(@RequestBody NganhCreationRequest request){
        return ApiResponse.<NganhResponse>builder()
                .result(nganhService.createNganh(request))
                .build();
    }

    @PutMapping("/{nganhId}")
    public ApiResponse<NganhResponse> updateNganh(@PathVariable("nganhId") String nganhId, @RequestBody NganhUpdateRequest request){
        return ApiResponse.<NganhResponse>builder()
                .result(nganhService.updateNganh(nganhId, request))
                .build();
    }

    @GetMapping("/{nganhId}")
    public ApiResponse<NganhResponse> getNganhById(@PathVariable("nganhId") String nganhId){
        return ApiResponse.<NganhResponse>builder()
                .result(nganhService.getNganhById(nganhId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<NganhResponse>> getAllNganh(){
        return ApiResponse.<List<NganhResponse>>builder()
                .result(nganhService.getAllNganh())
                .build();
    }

    @DeleteMapping("/{nganhId}")
    public void deleteNganhById(@PathVariable("nganhId") String nganhId){
        nganhService.deleteNganh(nganhId);
    }
}
