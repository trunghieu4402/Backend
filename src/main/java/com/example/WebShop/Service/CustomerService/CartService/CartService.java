package com.example.WebShop.Service.CustomerService.CartService;

import com.example.WebShop.Entity.CartItem;
import com.example.WebShop.dto.AddProductInCartDto;
import com.example.WebShop.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CartService {
     public  ResponseEntity<?> GetCart(Long id_cus);
    public ResponseEntity<?> AddToCart(AddProductInCartDto data);
    public ResponseEntity<?> RemoveItem(AddProductInCartDto product);
    public ResponseEntity<?> RemoveAllOfItem(AddProductInCartDto product);
}
