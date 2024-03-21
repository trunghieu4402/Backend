package com.example.WebShop.dto;

import com.example.WebShop.Entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrder {
    private List<ItemDto>ListItem;
    private Long address_id;
}
