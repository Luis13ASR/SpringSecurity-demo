package com.example.demo.Services.Impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTOs.ApiResponseDTO;
import com.example.demo.DTOs.RolesDTO;
import com.example.demo.Entities.RolesEntity;
import com.example.demo.Repositories.RolRepository;
import com.example.demo.Services.RolesService;
import com.example.demo.Utils.Meta;

@Service
public class RolesServiceImpl implements RolesService{

    private RolRepository rolRepository;

    public RolesServiceImpl(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public ResponseEntity<ApiResponseDTO<String>>  create(RolesDTO rolesDTO){

        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setName(rolesDTO.getName().toUpperCase());
        rolRepository.save(rolesEntity);
        ApiResponseDTO<String> response = new ApiResponseDTO<>(new Meta(HttpStatus.CREATED), "The rol was created successfully");
        return ResponseEntity.ok().body(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponseDTO<List<RolesDTO>>> findAll(){
    
        List<RolesEntity> roles = rolRepository.findAll();
        List<RolesDTO> dtos = roles.stream().map(rol -> rolesMapper(rol)).toList();
        ApiResponseDTO<List<RolesDTO>> response = new ApiResponseDTO<>(new Meta(HttpStatus.OK), dtos);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponseDTO<RolesDTO>> findById(Long id){

        ApiResponseDTO<RolesDTO> response = new ApiResponseDTO<>(new Meta(HttpStatus.FOUND), rolesMapper(rolRepository.findById(id).get()));
        return ResponseEntity.ok(response);
    }

    private RolesDTO rolesMapper(RolesEntity entity){
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setId(entity.getId());
        rolesDTO.setName(entity.getName());
        return rolesDTO;
    }

    private Boolean idValidation(Long id){
        return rolRepository.existsById(id);
    }
}
