package com.example.WebShop.Controller.Customer;

import com.example.WebShop.Service.CustomerService.OrderService.OrderService;
import com.example.WebShop.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/Order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping()
    public ResponseEntity<?> getOrder(@RequestParam("id") Long id)
    {
        System.out.println("HIHIHI");
        System.out.println(this.orderService.getAllOrder(id));
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/Add")
    public ResponseEntity<?> addOrder(@RequestParam("id")Long id,
                                      @RequestBody List<ProductDto> productDto)
    {
        this.orderService.addOrder(id,productDto);
        return ResponseEntity.ok("ok");
    }
}
