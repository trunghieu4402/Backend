package com.example.WebShop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;
    @Column(name = "img_name")
    private String img_name;
    @Column(name = "img_type")
    private String img_type;
    @Column( name = "picByte", length = 50000000)
    private byte[]picByte;
}
