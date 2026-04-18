package com.example.springsecurity.demo.dto;

import com.example.springsecurity.demo.entity.enums.Permission;
import com.example.springsecurity.demo.entity.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SingUpDto {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permission> permissions;
}
