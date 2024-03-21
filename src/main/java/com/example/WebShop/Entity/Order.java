package com.example.WebShop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName ="id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "address_id",nullable = false,referencedColumnName ="id")
    @JsonIgnore
    private Address address;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderItem> orderItems;
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
//    private Set<CartItem> cartItems;
}
