package com.example.WebShop.Repository;

import com.example.WebShop.Entity.Order;
import com.example.WebShop.Entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByUserIdAndStatus(Long user_id, OrderStatus status);
    List<Order> findByUserId(Long id);
}
