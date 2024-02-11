package com.example.WebShop.Repository;

import com.example.WebShop.Entity.Role;
import com.example.WebShop.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findFirstByEmail(String email);
    Optional<UserEntity> findByRole(Role role);

    List<UserEntity> findAllByRole(Role role);

}
