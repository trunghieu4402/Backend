package com.example.WebShop.Service.AdminService.UserService;

import com.example.WebShop.Entity.Role;
import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Repository.UserRepository;
import com.example.WebShop.dto.userDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UerService{
    @Autowired
    UserRepository userRepository;
    private ModelMapper mapper=new ModelMapper();
    public List<userDTO> getALlCustomer()
    {
        List<UserEntity> ListAccount = this.userRepository.findAllByRole(Role.CUSTOMER);
//        Optional<UserEntity> listCus=this.userRepository.findByRole(Role.CUSTOMER);
        List<userDTO> userDTOS = new ArrayList<>();
        ListAccount.forEach(i->
        {
            userDTO userDTO = this.mapper.map(i, userDTO.class);
            userDTOS.add(userDTO);
        });
        return userDTOS;

    }

}
