package com.example.WebShop.Controller.admin;

import com.example.WebShop.Service.AdminService.ProductService.ProductService;
import com.example.WebShop.dto.ImageDto;
import com.example.WebShop.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    private List<ProductDto> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/SearchByID")
    private ProductDto getProductsByID(@RequestParam("id") Long id)
    {
        return this.productService.searchProductByID(id);
    }

    @GetMapping("/search")
    private ResponseEntity<?> SearchProduct(@RequestParam("value") String value, @RequestParam("id") Long id) {

        return this.productService.SearchProduct(value,id);
    }

    @PostMapping(value = "/createProduct",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity<?> createProduct(@RequestPart("product") ProductDto productDto,
                                            @RequestPart("ImageFile")MultipartFile[] file)

    {
//        ProductDto productDto1 = productDto;
//        System.out.println(productDto.getPro_id());
//        return ResponseEntity.ok(this.productService.addProduct(productDto));
        try {
           Set<ImageDto> ListImg = upLoadImage(file);
//            System.out.println(ListImg);
            productDto.setProducts_img(ListImg);
//            System.out.println("Img from Dto: "+productDto.getImages());
            return ResponseEntity.ok(this.productService.addProduct(productDto));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;

        }
    }
    public Set<ImageDto> upLoadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageDto> imageDtos = new HashSet<>();
            for(MultipartFile file: multipartFiles)
            {
                ImageDto image = new ImageDto();
                image.setImg_name(file.getOriginalFilename());
                image.setImg_type(file.getContentType());
                image.setPicByte(file.getBytes());

//                ImageDto image = new ImageDto(
//                        file.getOriginalFilename(),
//                        file.getContentType(),
//                        file.getBytes()
//                );
                System.out.println("ImgDTO : "+image.getImg_name());
                System.out.println("ImgDTO : "+image.getImg_type());
//                System.out.println("ImgDTO : "+ Arrays.toString(image.getPicByte()));
                imageDtos.add(image);
            }
            return imageDtos;
    }

    @DeleteMapping("/deleteProduct")
    private ResponseEntity<?> DeleteProducts(@RequestParam("id") Long id) {

            this.productService.DeleteProduct(id);
            return ResponseEntity.ok("successfully");
    }
    @PutMapping("/updateProduct")
    private ResponseEntity<?> UpdateProduct(@RequestPart("product") ProductDto productDto,
                                            @RequestPart("ImageFile")MultipartFile[] file) throws IOException {
            Set<ImageDto> listImg= this.upLoadImage(file);
            productDto.setProducts_img(listImg);
            this.productService.UpdateProducts(productDto);
            return ResponseEntity.noContent().build();
    }

}
