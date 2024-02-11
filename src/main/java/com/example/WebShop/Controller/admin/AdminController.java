package com.example.WebShop.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String admin()
    {
        return "ddaya laf trag admin";
    }

}
