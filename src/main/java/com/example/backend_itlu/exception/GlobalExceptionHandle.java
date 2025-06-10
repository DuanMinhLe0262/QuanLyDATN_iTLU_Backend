package com.example.backend_itlu.exception;

import com.example.backend_itlu.dto.request.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandle {
        @ExceptionHandler(AppException.class)
        public ResponseEntity<ApiResponse> handleAppException(AppException ex) {
            ErrorCode errorCode = ex.getErrorCode();

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMessage(errorCode.getMessage());


            return ResponseEntity.badRequest().body(apiResponse);
        }
}
