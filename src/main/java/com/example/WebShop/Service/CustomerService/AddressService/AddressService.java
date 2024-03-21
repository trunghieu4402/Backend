package com.example.WebShop.Service.CustomerService.AddressService;

import com.example.WebShop.Entity.Address;
import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Filter.JwtRequestFilter;
import com.example.WebShop.Repository.AddressRepository;
import com.example.WebShop.Repository.UserRepository;
import com.example.WebShop.Service.AdminService.UserService.UerService;
import com.example.WebShop.dto.AddressDto;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService implements AddressServiceImpl {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtRequestFilter requestFilter;
    private ModelMapper mapper = new ModelMapper();
    private Gson gson= new Gson();


    public ResponseEntity<?> CreateAddress(String email, AddressDto addressDto)
    {
//        UserEntity user = this.userRepository.findFirstByEmail(this.requestFilter.getUserDetails().getUsername()).get();
        Optional<UserEntity> u = this.userRepository.findFirstByEmail(email);
        if(u.isEmpty())
        {
            return new ResponseEntity<>("User is not exist", HttpStatus.NOT_FOUND);
        }
        UserEntity user= u.get();
        Address address = this.mapper.map(addressDto,Address.class);
        address.setUser(user);
        this.addressRepository.save(address);
//        Set<Address> addressSet=user.getAddress();
//        addressSet.add(address);
//        user.setAddress(addressSet);
//        this.userRepository.save(user);

        return new ResponseEntity<>(address,HttpStatus.OK);
    }
    public ResponseEntity<?> getUserLocation(String email)
    {
        Optional<UserEntity> user = this.userRepository.findFirstByEmail(email);
        if(user.isEmpty())
        {
            return  new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        else
        {
            List<Address> address = this.addressRepository.findAllByUserId(user.get().getId());
          if(address.isEmpty())
          {
              return new ResponseEntity<>(gson.toJson("Location is empty"),HttpStatus.OK);
          }
          else
          {
              List<AddressDto> addressDtos = new ArrayList<>();
              address.forEach(i->
              {
                  addressDtos.add(mapper.map(i,AddressDto.class));
              });
              return new ResponseEntity<>(addressDtos,HttpStatus.OK);
          }
        }

    }
    public ResponseEntity<?> DeleteAddress(Long id)
    {
        UserEntity user = this.userRepository.findFirstByEmail(this.requestFilter.getUserDetails().getUsername()).get();
        Optional<Address> address = this.addressRepository.findAddressByIdAndUserId(user.getId(),id);
        if(address.isEmpty())
        {
            return new ResponseEntity<>("The Address is not exist",HttpStatus.NOT_FOUND);
        }
        this.addressRepository.delete(address.get());
        return new ResponseEntity<>(gson.toJson("Delete Complete"),HttpStatus.OK);
    }
}
