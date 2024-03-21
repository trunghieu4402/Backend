package com.example.WebShop.dto;

import lombok.Data;

@Data
public class AddressDto {
   private Long id;
    private String province;
    private String district;
    private String town;
    private String streetNumber;
    private String phoneNumber;
    private String Name;
}
