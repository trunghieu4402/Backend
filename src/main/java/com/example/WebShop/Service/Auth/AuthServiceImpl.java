package com.example.WebShop.Service.Auth;

import com.example.WebShop.Entity.*;
import com.example.WebShop.Repository.CartRepository;
import com.example.WebShop.Repository.OrderRepository;
import com.example.WebShop.Repository.UserRepository;
import com.example.WebShop.dto.signupRequest;
import com.example.WebShop.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    public userDTO signup(signupRequest signupRequest )
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        userEntity.setName(signupRequest.getName());
        userEntity.setRole(Role.CUSTOMER);
        userEntity.setPhone(signupRequest.getPhone());
       UserEntity user= userRepository.save(userEntity);

        Cart cart = new Cart();
        cart.setTotal_amount(0D);
//        order.setAmount(0D);
        cart.setUser(user);
        this.cartRepository.save(cart);

        userDTO userDTO = new userDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setPhone(userEntity.getPhone());

        return userDTO;
    }
    public Boolean CheckUsername(String email)
    {
        return  userRepository.findFirstByEmail(email).isPresent();
    }

}
