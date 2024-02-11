package com.example.WebShop.Service.CustomerService.OrderService;

import com.example.WebShop.Repository.OrderRepository;
import com.example.WebShop.Repository.ProductRepository;
import com.example.WebShop.Repository.UserRepository;
import com.example.WebShop.dto.OrderDto;
import com.example.WebShop.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    private ModelMapper mapper = new ModelMapper();
    public List<OrderDto> getAllOrder(Long id_customer)
    {
        List<OrderDto> list = new ArrayList<>();
//        this.orderRepository.findByUser_Id(id_customer).forEach(
//                i->
//                {
//                    OrderDto orderDto = this.mapper.map(i,OrderDto.class);
//                    list.add(orderDto);
//                }
//        );
        return list;

    }
    public void addOrder(Long id, List<ProductDto> productDto)
    {
    }

}
