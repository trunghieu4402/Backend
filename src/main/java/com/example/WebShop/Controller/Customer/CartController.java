package com.example.WebShop.Controller.Customer;


import com.example.WebShop.Filter.JwtRequestFilter;
import com.example.WebShop.Service.CustomerService.CartService.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/Cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
     private JwtRequestFilter jwtRequestFilter;
    @PostMapping("/add")
    public ResponseEntity<?> addtoCart(@RequestBody Long pro_id)
    {
        String email=jwtRequestFilter.getUserDetails().getUsername();
       return cartService.AddToCart(pro_id,email);
    }
//    @GetMapping("")
//    public ResponseEntity<?> getCart(@RequestParam("id") Long id)
//    {
//        return this.cartService.GetCart(id);
//    }
    @GetMapping("")
    public ResponseEntity<?> getCart()
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
//        return new ResponseEntity<>(userDetails.getUsername(), HttpStatus.OK);

        return this.cartService.GetCart(userDetails.getUsername());
    }


    @PostMapping("/deleteItem")
    public ResponseEntity<?> RemoveItem(@RequestBody  Long pro_id )
    {
        String email=jwtRequestFilter.getUserDetails().getUsername();
        return cartService.RemoveItem(pro_id, email);

    }
    @PostMapping("/deleteAllOfItem")
    public ResponseEntity<?> RemoveAllOfItem(@RequestBody  Long pro_id  )
    {
        String email=jwtRequestFilter.getUserDetails().getUsername();
        return cartService.RemoveAllOfItem(pro_id,email);
    }
}
