package com.example.WebShop.Service.CustomerService.CartService;

import com.example.WebShop.Entity.*;
import com.example.WebShop.Repository.*;
import com.example.WebShop.dto.AddProductInCartDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
            private CartRepository cartRepository;
    ModelMapper mapper = new ModelMapper();
    public ResponseEntity<?> AddToCart(AddProductInCartDto data)
    {
        Cart activeCart = cartRepository.findByUserId(data.getUser_id());
        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartId(data.getPro_id(),activeCart.getCart_id());
        if(optionalCartItem.isPresent())
        {
            Optional<ProductEntity> optionalProduct = this.productRepository.findById(data.getPro_id());
            Optional<UserEntity> optionalUser= this.userRepository.findById(data.getUser_id());
            if(optionalProduct.isPresent() && optionalUser.isPresent())
            {
                CartItem cartItem = optionalCartItem.get();
                cartItem.setQuantity(optionalCartItem.get().getQuantity() + 1);
                this.cartItemRepository.save(cartItem);
                Double price = cartItem.getProduct().getPro_Price()-(cartItem.getProduct().getPro_Price()*cartItem.getProduct().getPro_Discount()/100);

                activeCart.setTotal_amount(activeCart.getTotal_amount()+price);
                cartRepository.save(activeCart);
                return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
        else
        {
            Optional<ProductEntity> optionalProduct = this.productRepository.findById(data.getPro_id());
            Optional<UserEntity> optionalUser= this.userRepository.findById(data.getUser_id());
            if(optionalProduct.isPresent() && optionalUser.isPresent())
            {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(optionalProduct.get());
                cartItem.setQuantity(1L);
                cartItem.setCart(activeCart);
                CartItem updateCart= cartItemRepository.save(cartItem);
                Double price = cartItem.getProduct().getPro_Price()-(cartItem.getProduct().getPro_Price()*cartItem.getProduct().getPro_Discount()/100);
                activeCart.setTotal_amount(activeCart.getTotal_amount()+price);
                activeCart.getCartItems().add(cartItem);
//                activeCart.setCartItems(activeCart.getCartItems());
                cartRepository.save(activeCart);
                return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }
    public ResponseEntity<?> GetCart(Long id_cus)
    {
       if(this.userRepository.existsById(id_cus))
       {
           Cart Cart = this.cartRepository.findByUserId(id_cus);
           Cart.setUser(null);
           Set<CartItem>CartItem = this.cartItemRepository.findByCartId(Cart.getCart_id());
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(Cart);
       }
       else
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not exist");
       }
    }
    public ResponseEntity<?> RemoveItem(AddProductInCartDto product)
    {
        Cart activeCart = cartRepository.findByUserId(product.getUser_id());
        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartId(product.getPro_id(),activeCart.getCart_id());
       if (this.userRepository.existsById(product.getUser_id()))
       {
           if(optionalCartItem.isPresent())
           {
               CartItem item = optionalCartItem.get();
               item.setQuantity(item.getQuantity()-1);
               if(item.getQuantity()==0)
               {
                   this.cartItemRepository.delete(item);
               }
               else
               {
                   this.cartItemRepository.save(item);
               }
               Double price = item.getProduct().getPro_Price()-(item.getProduct().getPro_Price()*item.getProduct().getPro_Discount()/100);

               activeCart.setTotal_amount(activeCart.getTotal_amount()-price);
               Cart update= this.cartRepository.save(activeCart);
               return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
           }
           else
           {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product is not exist");

           }
       }
       else
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
       }
    }
    public ResponseEntity<?> RemoveAllOfItem(AddProductInCartDto product)
    {
        Cart activeCart = cartRepository.findByUserId(product.getUser_id());
        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartId(product.getPro_id(),activeCart.getCart_id());
        if (this.userRepository.existsById(product.getUser_id()))
        {
            if(optionalCartItem.isPresent())
            {
                CartItem item = optionalCartItem.get();
                long n = item.getQuantity();
                this.cartItemRepository.delete(item);
                Double price = n*(item.getProduct().getPro_Price()-(item.getProduct().getPro_Price()*item.getProduct().getPro_Discount()/100));
                activeCart.setTotal_amount(activeCart.getTotal_amount()-price);
                Cart update= this.cartRepository.save(activeCart);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product is not exist");

            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

}

