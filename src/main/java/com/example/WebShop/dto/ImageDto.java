package com.example.WebShop.dto;

import lombok.Data;

@Data
public class ImageDto {
   private String img_name;
    private String img_type;
    private byte[]picByte;
//    public ImageDto(String name, String type, byte[] bytes)
//    {
//        this.img_name=name;
//        this.img_type=type;
//        this.picByte= bytes;
//    }
}
