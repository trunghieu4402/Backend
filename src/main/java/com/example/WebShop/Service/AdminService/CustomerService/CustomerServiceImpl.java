package com.example.WebShop.Service.AdminService.CustomerService;

import org.springframework.http.ResponseEntity;

public interface CustomerServiceImpl {
    public ResponseEntity<?> GetCustomer(Long cus_id);
}
