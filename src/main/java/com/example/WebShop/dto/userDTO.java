package com.example.WebShop.dto;

import com.example.WebShop.Entity.Order;
import com.example.WebShop.Entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class userDTO {
    private String username;
    private String email;
    private byte[] avatar;
    private Role role;
    private String phone;
    private boolean IsActive;
    private Set<com.example.WebShop.Entity.Order> Order= new HashSet<>();
}
