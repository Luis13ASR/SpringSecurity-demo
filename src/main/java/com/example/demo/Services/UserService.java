package com.example.demo.Services;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.DTOs.UserDTO;

@Service
public interface UserService {
    
    public ResponseEntity<String> create(UserDTO user);
}
