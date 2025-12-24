package com.example.demo.Utils;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    
    private String transactionCode;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

    public Meta(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        timestamp = LocalDateTime.now();
        transactionCode = UUID.randomUUID().toString();
    }
}
