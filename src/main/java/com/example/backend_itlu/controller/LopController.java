package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.ApiResponse;
import com.example.backend_itlu.dto.request.LopCreationRequest;
import com.example.backend_itlu.dto.request.LopUpdateRequest;
import com.example.backend_itlu.dto.response.LopResponse;
import com.example.backend_itlu.service.LopService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lop")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class LopController {

    LopService lopService;

    @PostMapping
    public ApiResponse<LopResponse> createLop(@RequestBody LopCreationRequest request){
        return ApiResponse.<LopResponse>builder()
                .result(lopService.createLop(request))
                .build();
    }

    @PutMapping("/{lopId}")
    public ApiResponse<LopResponse> updatelop(@PathVariable("lopId") String lopId, @RequestBody LopUpdateRequest request){
        return ApiResponse.<LopResponse>builder()
                .result(lopService.updateLop(lopId, request))
                .build();
    }

    @GetMapping("/{lopId}")
    public ApiResponse<LopResponse> getLopById(@PathVariable("lopId") String lopId){
        return ApiResponse.<LopResponse>builder()
                .result(lopService.getLopById(lopId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<LopResponse>> getAllLop(){
        return ApiResponse.<List<LopResponse>>builder()
                .result(lopService.getAllLop())
                .build();
    }

    @DeleteMapping("/{lopId}")
    public void deleteLop(@PathVariable("lopId") String lopId){
        lopService.deleteLop(lopId);
    }
}
