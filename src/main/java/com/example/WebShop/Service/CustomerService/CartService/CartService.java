package com.example.WebShop.Service.CustomerService.CartService;

import org.springframework.http.ResponseEntity;

public interface CartService {
     public  ResponseEntity<?> GetCart(String email);
    public ResponseEntity<?> AddToCart(Long id,String email);
    public ResponseEntity<?> RemoveItem( Long id,String email);
    public ResponseEntity<?> RemoveAllOfItem(Long id,String email);
}
