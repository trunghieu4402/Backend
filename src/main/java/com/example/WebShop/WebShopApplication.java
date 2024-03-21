package com.example.WebShop;

import com.example.WebShop.Entity.Role;
import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class WebShopApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class,args);
//		SpringApplication.run(WebShopApplication.class,args);


	}
	@PostConstruct
	public void run() {
		Optional<UserEntity> adminAccount = this.userRepository.findByRole(Role.ADMIN);
		if (adminAccount.isEmpty()) {
			UserEntity user = new UserEntity();
			user.setName("admin");
			user.setEmail("admin@gmail.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("123456"));
			this.userRepository.save(user);
		}

	}
}
