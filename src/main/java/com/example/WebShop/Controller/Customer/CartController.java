package com.example.WebShop.Controller.Customer;


import com.example.WebShop.Entity.CartItem;
import com.example.WebShop.Entity.ProductEntity;
import com.example.WebShop.Repository.UserRepository;
import com.example.WebShop.Service.CustomerService.CartService.CartService;
import com.example.WebShop.dto.AddProductInCartDto;
import com.example.WebShop.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/Cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<?> addtoCart(@RequestBody AddProductInCartDto product)
    {
       return cartService.AddToCart(product);
    }
    @GetMapping("")
    public ResponseEntity<?> getCart(@RequestParam("id") Long id)
    {
        return this.cartService.GetCart(id);
    }


    @PostMapping("/deleteItem")
    public ResponseEntity<?> RemoveItem(@RequestBody  AddProductInCartDto product )
    {
        return cartService.RemoveItem(product);

    }
    @PostMapping("/deleteAllOfItem")
    public ResponseEntity<?> RemoveAllOfItem(@RequestBody  AddProductInCartDto product )
    {
        return cartService.RemoveAllOfItem(product);
    }
}
