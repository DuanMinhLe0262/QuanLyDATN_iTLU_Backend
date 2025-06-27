package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.ApiResponse;
import com.example.backend_itlu.dto.request.DeTaiGoiYCreationRequest;
import com.example.backend_itlu.dto.request.DeTaiGoiYUpdateRequest;
import com.example.backend_itlu.dto.response.DeTaiGoiYResponse;
import com.example.backend_itlu.service.DeTaiGoiYService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/detaigoiy")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DeTaiGoiYController {

    DeTaiGoiYService DeTaiGoiYService;
    @PostMapping
    public ApiResponse<DeTaiGoiYResponse> createDeTai(@RequestBody DeTaiGoiYCreationRequest request){
        return ApiResponse.<DeTaiGoiYResponse>builder()
                .result(DeTaiGoiYService.createDeTaiGoiY(request))
                .build();
    }

    @PutMapping("/{deTaiId}")
    public ApiResponse<DeTaiGoiYResponse> updateDeTai(@PathVariable("deTaiId") String deTaiId, @RequestBody DeTaiGoiYUpdateRequest request){
        return ApiResponse.<DeTaiGoiYResponse>builder()
                .result(DeTaiGoiYService.updateDeTaiGoiY(deTaiId, request))
                .build();
    }

    @GetMapping("/{deTaiId}")
    public ApiResponse<DeTaiGoiYResponse> getDeTaiById(@PathVariable("deTaiId") String deTaiId){
        return ApiResponse.<DeTaiGoiYResponse>builder()
                .result(DeTaiGoiYService.getDeTaiGoiYById(deTaiId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<DeTaiGoiYResponse>> getAllDeTai(){
        return ApiResponse.<List<DeTaiGoiYResponse>>builder()
                .result(DeTaiGoiYService.getAllDeTaiGoiY())
                .build();
    }

    @DeleteMapping("/{deTaiId}")
    public void deleteDeTaiById(@PathVariable("deTaiId") String deTaiId){
        DeTaiGoiYService.deleteDeTaiGoiY(deTaiId);
    }
}
