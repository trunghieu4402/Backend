package com.example.WebShop;

import com.example.WebShop.Entity.Role;
import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WebShopApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class,args);
		SpringApplication.run(WebShopApplication.class,args);
	}
	public void run(String... arg) {
		UserEntity adminAccount = userRepository.findByRole(Role.ADMIN).get();
		if (adminAccount==null) {
			UserEntity user = new UserEntity();
			user.setName("admin");
			user.setEmail("admin2@gmail.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("123"));
			userRepository.save(user);
		}
	}
}
