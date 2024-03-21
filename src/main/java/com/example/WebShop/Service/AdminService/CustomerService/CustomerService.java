package com.example.WebShop.Service.AdminService.CustomerService;

import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements CustomerServiceImpl{
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<?> GetCustomer(Long cus_id)
    {
        Optional<UserEntity> user = this.userRepository.findById(cus_id);
        if(user.isEmpty())
        {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        UserEntity user1=user.get();
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }
}
