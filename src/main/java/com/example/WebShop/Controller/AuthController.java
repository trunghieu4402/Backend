package com.example.WebShop.Controller;

import com.example.WebShop.Entity.UserEntity;
import com.example.WebShop.Repository.UserRepository;
import com.example.WebShop.Service.AdminService.ProductService.ProductService;
import com.example.WebShop.Service.Auth.AuthService;
import com.example.WebShop.Utils.JwtUtil;
import com.example.WebShop.dto.AuthenticationRequest;
import com.example.WebShop.dto.ProductDto;
import com.example.WebShop.dto.signupRequest;
import com.example.WebShop.dto.userDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequestMapping("/base")
@RestController
@Controller
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final ProductService productService;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthService authService;
    public static final String HEADER_STRING="Authorization";
    public static final String TOKEN_PREFIX="Bearer ";
    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }
        catch ( BadCredentialsException e)
        {
            throw  new BadCredentialsException("incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        System.out.println("userDetails: "+ userDetails);
        Optional<UserEntity> user = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        if(user.isPresent())
        {
//            System.out.println(user.get().getRole());
            response.getWriter().write(new JSONObject()
                    .put("username",user.get().getUsername())
                    .put("role", user.get().getRole())
                    .put("user_id",user.get().getId())
                    .toString()
            );
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING,TOKEN_PREFIX+jwt );
        }

    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody signupRequest signupRequest)
    {
        if(authService.CheckUsername(signupRequest.getEmail()))
        {
            return new ResponseEntity<>("username is already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        userDTO userDTO = new userDTO();
        userDTO=authService.signup(signupRequest);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
//    @GetMapping(name = "/Search")
//    public List<ProductDto> SearchProducts(@RequestPart String key)
//    {
//        List<ProductDto> List= new ArrayList<>();
//        List<ProductDto> productDtos = new ArrayList<>();
//        if (!key.isEmpty())
//        {
//            List=this.productService.SearchProductByName(key);
//        }
//        List.forEach(
//            i->
//            {
//                ProductDto productDto = new ProductDto();
//                productDto.setPro_id(i.getPro_id());
//                productDto.setProducts_img(i.getProducts_img());
//                productDto.setPro_Name(i.getPro_Name());
//                productDto.setPro_Price(i.getPro_Price());
//                productDto.setPro_Discount(i.getPro_Discount());
//                productDto.setPro_Describe(i.getPro_Describe());
//                productDtos.add(productDto);
//            }
//        );
//        return productDtos;
//    }
@GetMapping("/SearchByID")
private ProductDto getProductsByID(@RequestParam("id") Long id)
{
    ProductDto dto =this.productService.searchProductByID(id);
    ProductDto productDto = new ProductDto();
    productDto.setProducts_img(dto.getProducts_img());
    productDto.setPro_id(dto.getPro_id());
    productDto.setPro_Name(dto.getPro_Name());
    productDto.setPro_Price(dto.getPro_Price());
    productDto.setPro_Describe(dto.getPro_Describe());
    productDto.setPro_Discount(dto.getPro_Discount());
    return productDto;
}
    @GetMapping("/search")
    private ResponseEntity<?> SearchProduct(@RequestParam("value") String value, @RequestParam("id") Long id)
    {
         return productService.SearchProduct(value,id);
    }
    @GetMapping(name = "/")
    public List<ProductDto> getProducts()
    {
        return this.TransferProduct(this.productService.getAllProducts());
    }
    public List<ProductDto> TransferProduct(List<ProductDto> productDtos)
    { List<ProductDto>list= new ArrayList<>();
        productDtos.forEach(
                i->
                {
                    ProductDto productDto = new ProductDto();
                    productDto.setPro_id(i.getPro_id());
                    productDto.setProducts_img(i.getProducts_img());
                    productDto.setPro_Name(i.getPro_Name());
                    productDto.setPro_Price(i.getPro_Price());
                    productDto.setPro_Discount(i.getPro_Discount());
                    productDto.setPro_Describe(i.getPro_Describe());
                    list.add(productDto);
                }
        );
        return list;

    }

}
