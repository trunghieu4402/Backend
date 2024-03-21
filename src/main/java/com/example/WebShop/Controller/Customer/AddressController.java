package com.example.WebShop.Controller.Customer;

import com.example.WebShop.Filter.JwtRequestFilter;
import com.example.WebShop.Service.CustomerService.AddressService.AddressServiceImpl;
import com.example.WebShop.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer/address")
public class AddressController {
    @Autowired
    private JwtRequestFilter requestFilter;
    @Autowired
    private AddressServiceImpl addressService;
    @PostMapping("CreateAddress")
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto)
    {
        String email= this.requestFilter.getUserDetails().getUsername();
        return this.addressService.CreateAddress(email,addressDto);
    }
    @GetMapping("")
            public ResponseEntity<?> getAddress()
    {
        String email = this.requestFilter.getUserDetails().getUsername();
        return this.addressService.getUserLocation(email);
    }
    @DeleteMapping("DeleteAddress")
    public ResponseEntity<?> DeleteAddress(@RequestParam("id") Long id)
    {
        System.out.println("delete");
        return this.addressService.DeleteAddress(id);
    }
}
