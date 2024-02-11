package com.example.WebShop.dto;

import lombok.Data;

@Data
public class signupRequest {
    private String name;
    private String password;
    private String email;
    private String phone;
}
