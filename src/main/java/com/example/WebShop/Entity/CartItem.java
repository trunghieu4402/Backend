package com.example.WebShop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
//@Entity
@Table(name = "cart_item")
@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString

@Entity


public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cart_item_id;
    private Long quantity;

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
    @JoinColumn(name = "cart_id" , referencedColumnName = "cart_id")
    @JsonIgnore
    private Cart cart;

}
