package com.example.WebShop.dto;

import com.example.WebShop.Entity.CartItem;
import com.example.WebShop.Entity.ImageEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDto {
    private Long pro_id;
    private String pro_Name;
    private String pro_Describe;
    private int pro_Quantity;
    private Double pro_Price;
    private double pro_Discount;
    private Set<ImageDto> products_img;
    private List<CartItem> cartItems;
    private CategoryDto category;
//public ProductDto(String name, String des, int quanti, Long price,double discount, CategoryDto cate)
//{
//    this.pro_Name=name;
//    this.pro_Price=price;
//    this.category=cate;
//    this.pro_Quantity=quanti;
//    this.pro_Discount=discount;
//    this.pro_Describe=des;
////    this.images=new HashSet<>();
//
//}
}
