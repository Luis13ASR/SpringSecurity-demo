package com.example.demo.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.DTOs.ApiResponseDTO;
import com.example.demo.Utils.Meta;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<?>> defaultExceptionHandler(){
        ApiResponseDTO<String> response = new ApiResponseDTO<>(new Meta(HttpStatus.INTERNAL_SERVER_ERROR), "Something went wrong with create service");
        return ResponseEntity.internalServerError().body(response);
    }
}
