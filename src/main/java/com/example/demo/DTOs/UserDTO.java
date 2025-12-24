package com.example.demo.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class UserDTO {
    
    @Email
    private String email;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(min = 8, max = 15)
    private String password;

    @NotBlank
    private String rol;
}
