package com.example.WebShop.Service.Impl;

import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser= userRepository.findFirstByEmail(email);
//        System.out.println("ROLE"+optionalUser.get().getRole());
        if(optionalUser.isEmpty())
        {
            System.out.println("UserDetailsServiceImpl ko có gì");
            throw new UsernameNotFoundException("ko co gì",null);
        }
        return new User(optionalUser.get().getEmail(),optionalUser.get().getPassword(),optionalUser.get().getAuthorities());
    }
}
