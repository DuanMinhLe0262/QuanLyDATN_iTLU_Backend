package com.example.backend_itlu.controller;

import com.example.backend_itlu.dto.request.*;
import com.example.backend_itlu.dto.response.BoMonResponse;
import com.example.backend_itlu.dto.response.UserResponse;
import com.example.backend_itlu.service.BoMonService;
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
@RequestMapping("/bomon")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BoMonController {

    BoMonService boMonService;

    @PostMapping
    public ApiResponse<BoMonResponse> createBoMon(@RequestBody BoMonCreationRequest request){
        return ApiResponse.<BoMonResponse>builder()
                .result(boMonService.createBoMon(request))
                .build();
    }


    @GetMapping("/{boMonId}")
    public ApiResponse<BoMonResponse> getBoMon(@PathVariable ("boMonId") String boMonId){
        return ApiResponse.<BoMonResponse>builder()
                .result(boMonService.getBoMonById(boMonId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BoMonResponse>> getAllBoMons(){
        return ApiResponse.<List<BoMonResponse>>builder()
                .result(boMonService.getAllBoMons())
                .build();
    }

    @PutMapping("/{boMonId}")
    public ApiResponse<BoMonResponse> updateBoMon(@PathVariable ("boMonId") String boMonId, @RequestBody BoMonUpdateRequest request){
        return ApiResponse.<BoMonResponse>builder()
                .result(boMonService.updateBoMon(boMonId, request))
                .build();
    }

    @DeleteMapping("/{boMonId}")
    public void deleteBoMon(@PathVariable ("boMonId") String boMonId){
        boMonService.deleteBoMon(boMonId);
    }

}
