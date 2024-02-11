package com.example.WebShop.Repository;

import com.example.WebShop.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query(value = "SELECT * FROM cart_item where cart_id =:cart and pro_id =:pro" ,nativeQuery = true)
    Optional<CartItem> findByProductIdAndCartId(@Param("pro") Long pro_id,@Param("cart") Long cart_id);

    @Query(value = "SELECT * FROM cart_item where cart_id =:cart " ,nativeQuery = true)
    Set<CartItem> findByCartId(@Param("cart") Long cus_id);
}
