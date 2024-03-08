package com.example.WebShop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @CreationTimestamp
    LocalDateTime order_date;
    private Double total_amount;
    LocalDateTime DeliveryDate;
    private OrderStatus status;
    private UUID TrackingID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName ="id")
    private UserEntity user;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id",nullable = false,referencedColumnName ="id")
    private Address address;
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
//    private Set<CartItem> cartItems;
}
