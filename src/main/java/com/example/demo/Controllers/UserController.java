package com.example.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@Valid @RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }
}
