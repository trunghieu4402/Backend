package com.example.WebShop.Repository;

import com.example.WebShop.Entity.Order;
import com.example.WebShop.Entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByUserIdAndStatus(Long user_id, OrderStatus status);
}
