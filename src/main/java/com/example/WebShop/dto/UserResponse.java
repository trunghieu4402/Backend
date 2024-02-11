package com.example.WebShop.dto;

import com.example.WebShop.Entity.Role;
import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private Role Role;
    private String token;

}
