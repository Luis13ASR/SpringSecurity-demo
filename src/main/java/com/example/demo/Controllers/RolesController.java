package com.example.demo.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.ApiResponseDTO;
import com.example.demo.DTOs.RolesDTO;
import com.example.demo.Services.RolesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/roles")
public class RolesController {
    
    private RolesService rolesService;

    public RolesController(RolesService rolesService){
        this.rolesService = rolesService;
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponseDTO<String>> create(@Valid @RequestBody RolesDTO rolesDTO){
        return rolesService.create(rolesDTO);
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<RolesDTO>>> findAll(){
        return rolesService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDTO<RolesDTO>> findById(@PathVariable(value = "id") Long id){
        return rolesService.findById(id);
    }
}
