package com.example.WebShop.Controller.admin;

import com.example.WebShop.Service.AdminService.UserService.UerService;
import com.example.WebShop.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UerService uerService;
    @GetMapping("/Account")
    public List<userDTO> getAllAccount()
    {
        System.out.println("hihi");
       return this.uerService.getALlCustomer();

    }
}
