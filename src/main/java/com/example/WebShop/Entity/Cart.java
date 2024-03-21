package com.example.WebShop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;
//    private Double total_amount;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName ="id")
    private UserEntity user;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cart")
    private Set<CartItem> cartItems;
}
