package com.example.WebShop.Controller.Customer;

import com.example.WebShop.Entity.OrderStatus;
import com.example.WebShop.Filter.JwtRequestFilter;
import com.example.WebShop.Service.CustomerService.OrderService.OrderService;
import com.example.WebShop.dto.CreateOrder;
import com.example.WebShop.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/Order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @GetMapping("")
    public ResponseEntity<?> getOrderByUser()
    {
        return this.orderService.getAllOrder();
    }
    @PostMapping(value = "/CreateOrder",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addOrder(@RequestPart("ListItem") List<ItemDto> list,
                                      @RequestPart("address_id") Long id)
    {
        System.out.println("order nè");
        String email =this.jwtRequestFilter.getUserDetails().getUsername();
       return this.orderService.CreateOrder(email,list,id);
//        return ResponseEntity.ok("ok");
    }
//    @PostMapping(value = "/CreateOrder", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<?> addOrder(@RequestBody CreateOrder order)
//    {
//        System.out.println("order nè");
//        String email =this.jwtRequestFilter.getUserDetails().getUsername();
//        return this.orderService.CreateOrder(email,order.getListItem(),order.getAddress_id());
////        return ResponseEntity.ok("ok");
//    }
    @PostMapping("/updateOrder")
    public ResponseEntity<?> UpdateOrder(@RequestParam("id")Long id,
                                         @RequestBody OrderStatus status)
    {
        return this.orderService.UpdateOrderByUser(id,status);
    }
}
