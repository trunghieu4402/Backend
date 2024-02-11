package com.example.WebShop.Service.CustomerService.OrderService;

import com.example.WebShop.dto.OrderDto;
import com.example.WebShop.dto.ProductDto;

import java.util.List;

public interface OrderService {
    public List<OrderDto> getAllOrder(Long id_customer);
    public void addOrder(Long id, List<ProductDto> productDto);
}
