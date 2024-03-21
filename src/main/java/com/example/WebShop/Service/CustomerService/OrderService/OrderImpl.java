package com.example.WebShop.Service.CustomerService.OrderService;

import com.example.WebShop.Entity.*;
import com.example.WebShop.Err.GlobalExceptionHandler;
import com.example.WebShop.Filter.JwtRequestFilter;
import com.example.WebShop.Repository.*;
import com.example.WebShop.dto.AddressDto;
import com.example.WebShop.dto.ItemDto;
import com.example.WebShop.dto.OrderDto;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;
    //    @Autowired
//    ProductRepository productRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AddressRepository addressRepository;
    double amount = 0;
    @Autowired
    private JwtRequestFilter requestFilter;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    private ModelMapper mapper = new ModelMapper();
    private Gson gson = new Gson();

    public ResponseEntity<?> getAllOrder() {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(this.requestFilter.getUserDetails().getUsername());
        if(user.isEmpty())
        {
            return new ResponseEntity<>("User is not found",HttpStatus.NOT_FOUND);
        }
        List<Order> orderList = this.orderRepository.findByUserId(user.get().getId());
        List<OrderDto>orderDtos= new ArrayList<>();
        for(Order i:orderList)
        {
            AddressDto addressDto = this.mapper.map(i.getAddress(),AddressDto.class);
            OrderDto dto = this.mapper.map(i,OrderDto.class);
            dto.setAddressDto(addressDto);
            orderDtos.add(dto);
        }
        return new ResponseEntity<>(orderDtos,HttpStatus.OK);

    }

    public ResponseEntity<?> CreateOrder(String email, List<ItemDto> itemDtos, Long add_id) {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(email);
        this.amount=0;
            Order order = new Order();
            List<OrderItem> ListOrderItems = new ArrayList<>();
        Optional<Address> address = this.addressRepository.findAddressByIdAndUserId(user.get().getId(), add_id);
        if (address.isEmpty()) {
            return new ResponseEntity<>("Address is not exist", HttpStatus.NOT_FOUND);
        }
            Cart cart = this.cartRepository.findByUserId(user.get().getId());

        for(ItemDto i:itemDtos)
        {
            Optional<ProductEntity> pro = this.productRepository.findById(i.getId_product());
            if(pro.isPresent())
            {
                ProductEntity product = pro.get();
                int soluongtrongKHo= product.getPro_Quantity();
                if( soluongtrongKHo>=i.getQuantity())
                {
                    product.setPro_Quantity(product.getPro_Quantity()-i.getQuantity());
                    this.productRepository.save(product);
                }
                else
                {
                    return new ResponseEntity<>(gson.toJson("The quantity of product "+product.getPro_Name()+" in stock is not enough"),HttpStatus.OK);
                }

                Double price = product.getPro_Price() - (product.getPro_Price() * (product.getPro_Discount()/ 100));
                OrderItem orderItem = new OrderItem();
//                ProductEntity product = this.mapper.map(i.getProduct(),ProductEntity.class);
                orderItem.setProduct(product);
                orderItem.setPrice(product.getPro_Price());
                orderItem.setQuantity(i.getQuantity());
                OrderItem item = this.orderItemRepository.save(orderItem);
                amount += (product.getPro_Price() - (product.getPro_Price() * (product.getPro_Discount()/ 100)))*i.getQuantity();
                ListOrderItems.add(orderItem);
                Optional<CartItem> cartItem = this.cartItemRepository.findByProductIdAndCartId(i.getId_product(),cart.getCart_id());
                if(cartItem.isPresent())
                {
                    this.cartItemRepository.delete(cartItem.get());
                }
            }
            else
            {
                return new ResponseEntity<String>("Product is not exist",HttpStatus.NOT_FOUND);
            }

        }
            order.setOrderItems(ListOrderItems);
            order.setAddress(address.get());
            order.setOrder_date(LocalDateTime.now());
            order.setStatus(OrderStatus.Pending);
            order.setUser(user.get());
            order.setTotal_amount(this.amount);
            UUID newUuid = UUID.randomUUID();
            order.setTrackingID(newUuid);
            System.out.println("hihihih"+order.getOrderItems().size());
            Order order1=this.orderRepository.save(order);
            for(OrderItem item:ListOrderItems)
            {
                item.setOrder(order1);
                this.orderItemRepository.save(item);
            }
            return new ResponseEntity<>(gson.toJson("Order Complete"), HttpStatus.OK);
        }

    public ResponseEntity<?> UpdateOrderByUser(Long id, OrderStatus status) {
        this.requestFilter.getUserDetails().getAuthorities();
        Optional<Order> ord = this.orderRepository.findById(id);
        if (ord.isEmpty()) {
            return new ResponseEntity<>("Order Not Found", HttpStatus.NOT_FOUND);
        }
        if (this.requestFilter.getUserDetails().getAuthorities().equals(Role.CUSTOMER)) {
            Order order = ord.get();
            if (order.getStatus() == OrderStatus.Pending && !status.equals(OrderStatus.Cancel)) {
                return new ResponseEntity<>("Can Not Update Order Status", HttpStatus.EXPECTATION_FAILED);
            }
            if (order.getStatus() == OrderStatus.Delivery && status.equals(OrderStatus.Cancel) || order.getStatus() == OrderStatus.Receive && status.equals(OrderStatus.Cancel)) {
                return new ResponseEntity<>("Can Not Update Order Status", HttpStatus.EXPECTATION_FAILED);
            }
            order.setStatus(status);
            return new ResponseEntity<>(order,HttpStatus.OK);
//            order.setStatus();


        }
        return new ResponseEntity<>("null",HttpStatus.NOT_FOUND);
//        else {
//
//            if(order.getStatus()==OrderStatus.Pending && status==OrderStatus.Refund)
//            {
//                return new ResponseEntity<>("Cant Change Status",HttpStatus.EXPECTATION_FAILED);
//            } else if (order.getStatus()==OrderStatus.Shipped&& status== OrderStatus.Cancel) {
//                return new ResponseEntity<>("Cant Change Status",HttpStatus.EXPECTATION_FAILED);
//
//            } else if (order.getStatus()!=OrderStatus.Complete&& status==OrderStatus.Complete) {
//                order.setDeliveryDate(LocalDateTime.now());
//                order.setStatus(status);
//                this.orderRepository.save(order);
//                return  new ResponseEntity<>("Order Status is"+status,HttpStatus.OK);
//            } else {
//                order.setStatus(status);
//                this.orderRepository.save(order);
//                return  new ResponseEntity<>("Order Status is"+status,HttpStatus.OK);
//
//            }
//        }
    }
}
