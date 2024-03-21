package com.example.WebShop.dto;

import com.example.WebShop.Entity.OrderItem;
import com.example.WebShop.Entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long order_id;
    LocalDateTime order_date;
    LocalDateTime DeliveryDate;
    Double total_amount;
    private OrderStatus status;
    private UUID TrackingID;
    private List<OrderItem> orderItems;
    private AddressDto addressDto;
}
