package com.example.WebShop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Order_item_id;
    private int quantity;
    private Double Price;

    // annotation @ManyToOne để chỉ định quan hệ n-1 với bảng cart
//    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private UserEntity user;

    // annotation @ManyToOne để chỉ định quan hệ n-1 với bảng product
    @ManyToOne
    @JoinColumn(name = "pro_id",nullable = false,referencedColumnName = "pro_id")
    @OnDelete(action = OnDeleteAction.CASCADE)

    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_id" , referencedColumnName = "order_id")
    @JsonIgnore
    private Order order;




}
