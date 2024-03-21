package com.example.WebShop.Service.CustomerService.AddressService;

import com.example.WebShop.dto.AddressDto;
import org.springframework.http.ResponseEntity;

public interface AddressServiceImpl {
    public ResponseEntity<?> CreateAddress(String email, AddressDto addressDto);
    public ResponseEntity<?> getUserLocation(String email);
    public ResponseEntity<?> DeleteAddress(Long id);
}
