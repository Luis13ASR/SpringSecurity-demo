package com.example.demo.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DTOs.ApiResponseDTO;
import com.example.demo.DTOs.RolesDTO;

@Service
public interface RolesService {
    
    public ResponseEntity<ApiResponseDTO<String>> create(RolesDTO rolesDTO);
    public ResponseEntity<ApiResponseDTO<List<RolesDTO>>> findAll();
    public ResponseEntity<ApiResponseDTO<RolesDTO>> findById(Long id);
}
