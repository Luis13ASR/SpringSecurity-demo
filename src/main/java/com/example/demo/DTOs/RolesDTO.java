package com.example.demo.DTOs;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RolesDTO {
    
    @Id
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;
}
