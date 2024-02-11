package com.example.WebShop.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDto {
    private Long cate_id;
    private String cate_name;
    private String cate_Describe;
    private Set<ProductDto>products = new HashSet<ProductDto>();
}
