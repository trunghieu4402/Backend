package com.example.WebShop.Service.AdminService.UserService;

import com.example.WebShop.dto.userDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UerService {
    public List<userDTO> getALlCustomer();
}
