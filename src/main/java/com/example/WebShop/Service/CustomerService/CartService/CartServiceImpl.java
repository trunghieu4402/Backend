package com.example.WebShop.Service.CustomerService.CartService;

import com.example.WebShop.Entity.*;
import com.example.WebShop.Repository.*;
import com.google.gson.Gson;
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
    private static final Gson gson = new Gson();



    public ResponseEntity<?> AddToCart(Long id,String email)
    {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(email);
        if(user.isEmpty())
        {
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
        else
        {
            Cart activeCart = cartRepository.findByUserId(user.get().getId());
            Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartId(id,activeCart.getCart_id());
            if(optionalCartItem.isPresent())
            {
                Optional<ProductEntity> optionalProduct = this.productRepository.findById(id);
//                Optional<UserEntity> optionalUser= this.userRepository.findById(data.getUser_id());
                if(optionalProduct.isPresent())
                {
                    CartItem cartItem = optionalCartItem.get();
                    cartItem.setQuantity(optionalCartItem.get().getQuantity() + 1);
                    this.cartItemRepository.save(cartItem);
                    cartRepository.save(activeCart);
                    return new ResponseEntity<>(gson.toJson("Add complete"),HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
                }
            }
            else
            {
                Optional<ProductEntity> optionalProduct = this.productRepository.findById(id);
                if(optionalProduct.isPresent())
                {
                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(optionalProduct.get());
                    cartItem.setQuantity(1L);
                    cartItem.setCart(activeCart);
                    CartItem updateCart= cartItemRepository.save(cartItem);
                    activeCart.getCartItems().add(cartItem);
                    cartRepository.save(activeCart);
                    return new ResponseEntity<>(gson.toJson("Add complete"),HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
                }
            }

        }

    }
    public ResponseEntity<?> GetCart(String email)
    {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(email);
       if(user.isPresent())
       {
           Cart Cart = this.cartRepository.findByUserId(user.get().getId());
           Cart.setUser(null);
           Set<CartItem>CartItem = this.cartItemRepository.findByCartId(Cart.getCart_id());
           return new ResponseEntity<>(Cart,HttpStatus.OK);
       }
       else
       {
           return new ResponseEntity<>("User Not Fouund", HttpStatus.NOT_FOUND);
       }
    }
    public ResponseEntity<?> RemoveItem(Long id,String email)
    {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(email);
        if(user.isEmpty())
        {
            return new ResponseEntity<>("User Not Fouund", HttpStatus.NOT_FOUND);
        }
        else
        {
            Cart activeCart = cartRepository.findByUserId(user.get().getId());
            Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartId(id,activeCart.getCart_id());
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
                    Cart update= this.cartRepository.save(activeCart);
                    return new ResponseEntity<>(gson.toJson("Delete Complete"),HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>("Product Not Exist", HttpStatus.NOT_FOUND);
                }

        }

    }
    public ResponseEntity<?> RemoveAllOfItem(Long id,String email)
    {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(email);
        if (user.isEmpty())
        {
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND );
        }
        else
        {
            Cart activeCart = cartRepository.findByUserId(user.get().getId());
            Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartId(id,activeCart.getCart_id());
                if(optionalCartItem.isPresent())
                {
                    CartItem item = optionalCartItem.get();
                    this.cartItemRepository.delete(item);
                    Cart update= this.cartRepository.save(activeCart);
                    return new ResponseEntity<>(gson.toJson("Delete Complete"),HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>("Product Not Exist", HttpStatus.NOT_FOUND);

                }
            }

    }

}



