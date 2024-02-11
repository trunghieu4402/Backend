package com.example.WebShop.dto;

import com.example.WebShop.Entity.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long OrderId;
    Date OrderDate;
    Date DeliveryDate;
    private userDTO user;
    private OrderStatus status;
}
