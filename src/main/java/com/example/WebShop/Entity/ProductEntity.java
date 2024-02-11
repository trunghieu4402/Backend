package com.example.WebShop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pro_id;
    @Column(nullable = false)
    private String pro_Name;
    private String pro_Describe;
    private int pro_Quantity;
    @Column(nullable = false)
    private Double pro_Price;
    private double pro_Discount;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "product_image",joinColumns = {
            @JoinColumn(name = "pro_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "img_id")
    }
    )
    private Set<ImageEntity> products_img;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "Cate_id",nullable = false,referencedColumnName = "Cate_id")
    @JsonIgnore
    private CategoryEntity category;
//    @ManyToMany(mappedBy = "products")
//    Set<UserEntity>users;
}
