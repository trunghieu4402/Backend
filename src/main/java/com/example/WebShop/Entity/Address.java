package com.example.WebShop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String province;
    private String district;
    private String town;
    private String streetNumber;
    private String phoneNumber;
    private String Name;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName ="id")
    @JsonIgnore
    private UserEntity user;
    @OneToMany(mappedBy = "address")
    private Set<Order> Order= new HashSet<>();
}
