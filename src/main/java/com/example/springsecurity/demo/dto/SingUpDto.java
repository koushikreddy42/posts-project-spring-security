package com.example.springsecurity.demo.dto;

import lombok.Data;

@Data
public class SingUpDto {
    private String email;
    private String password;
    private String name;
}
