package com.example.WebShop.Service.CustomerService.OrderService;

import com.example.WebShop.Entity.OrderStatus;
import com.example.WebShop.dto.ItemDto;
import com.example.WebShop.dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    public ResponseEntity<?> getAllOrder();
    public ResponseEntity<?> CreateOrder(String email, List<ItemDto> itemDtos, Long address);
    public ResponseEntity<?> UpdateOrderByUser(Long id, OrderStatus status);
}
