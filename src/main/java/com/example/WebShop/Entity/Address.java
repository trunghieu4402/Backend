package com.example.WebShop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String City;
    private  String Department;
    private String District;
    private String StreetNumber;
    private String PhoneNumber;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName ="id")
    private UserEntity user;
    @OneToMany(mappedBy = "address")
    private Set<Order> Order= new HashSet<>();


}
