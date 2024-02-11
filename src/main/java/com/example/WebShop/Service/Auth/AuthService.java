package com.example.WebShop.Service.Auth;

import com.example.WebShop.dto.signupRequest;
import com.example.WebShop.dto.userDTO;

public interface AuthService {
    userDTO signup(signupRequest signupRequest );
    Boolean CheckUsername(String email);
}
