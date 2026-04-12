package com.example.springsecurity.demo.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
