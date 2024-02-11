package com.example.WebShop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long Cate_id;

     private String Cate_name;
     private String Cate_Describe;
     @OneToMany(mappedBy = "category")
     private Set<ProductEntity> Products= new HashSet<>();

}
