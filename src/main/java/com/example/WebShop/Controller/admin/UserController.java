package com.example.WebShop.Controller.admin;

import com.example.WebShop.Service.AdminService.CustomerService.CustomerService;
import com.example.WebShop.Service.AdminService.CustomerService.CustomerServiceImpl;
import com.example.WebShop.Service.AdminService.UserService.UerService;
import com.example.WebShop.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/Customer")
public class UserController {
    @Autowired
    UerService uerService;
    @Autowired
    private CustomerServiceImpl customerService;
    @GetMapping("/getAllUser")
    public List<userDTO> getAllAccount()
    {;
       return this.uerService.getALlCustomer();
    }
    @GetMapping("/getUserById")
    public ResponseEntity<?> getUser(@RequestParam("id") Long id)
    {;
        return this.customerService.GetCustomer(id);
    }
}
