package com.example.demo.DTOs;

import com.example.demo.Utils.Meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {
    
    private Meta meta;
    private T data;
}
