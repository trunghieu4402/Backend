package com.example.WebShop.Service.AdminService.ProductService;

import com.example.WebShop.Entity.ImageEntity;
import com.example.WebShop.Entity.ProductEntity;
import com.example.WebShop.Repository.CategoryRepository;
import com.example.WebShop.Repository.ImageRepository;
import com.example.WebShop.Repository.ProductRepository;
import com.example.WebShop.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private final ModelMapper mapper = new ModelMapper();
    public List<ProductDto> getAllProducts()
    {
        List<ProductEntity> productEntities = this.productRepository.findAll();
        List<ProductDto>productDtos= new ArrayList<>();
        for (ProductEntity p : productEntities)
        {
            productDtos.add(this.mapper.map(p,ProductDto.class));
        }
        return productDtos;
    }
    public ProductDto addProduct(ProductDto productDto)
    {
//        System.out.println("Dto: "+productDto.getProducts_img());
        ProductEntity product = this.mapper.map(productDto,ProductEntity.class);
//        System.out.println("products Img"+product.getProducts_img());
        ProductEntity productEntity=this.productRepository.save(product);
//        CategoryEntity category = this.categoryRepository.findById(productDto.getCategory().getCate_id()).get();
//        Set<ProductEntity> productEntities= new HashSet<>();
//        productEntities.add(this.productRepository.findById(productEntity.getPro_id()).get());
//        category.setProducts(productEntities);
        ProductEntity productEntity1 = this.productRepository.findById(productEntity.getPro_id()).get();
        return this.mapper.map(productEntity1,ProductDto.class);
    }
    public ProductDto searchProductByID(long id)
    {
        return  this.mapper.map(this.productRepository.findById(id).get(),ProductDto.class);
    }
    public ResponseEntity<?> SearchProduct(String value, Long id)
    {
        List<ProductDto> productDtos= new ArrayList<>();
        List<ProductEntity> productEntities = new ArrayList<>();
        if(!value.isEmpty() && id!=0)
        {
             productEntities= this.productRepository.FindProductEntityByName(value, id);
        } else if (!value.isEmpty()) {
            productEntities= this.productRepository.FindProductEntityByName(value);
        }
        else
        {
            productEntities= this.productRepository.findALLByCategory(id);
        }

        if (productEntities !=null)
        {
            for(ProductEntity s : productEntities)
            {
                productDtos.add(this.mapper.map(s,ProductDto.class));
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDtos);
    }
    public void DeleteProduct(Long id)
    {
        ProductEntity product = this.productRepository.findById(id).get();
        if (product!=null)
        {
            this.productRepository.deleteById(id);
        }
        else
        {

            throw  new NoSuchElementException();
        }
    }
    public void UpdateProducts(ProductDto productDto)
    {
        List<Long> id = new ArrayList<>();
        ProductEntity productEntity= this.productRepository.findById(productDto.getPro_id()).get();
        Set<ImageEntity> imageEntitySet = productEntity.getProducts_img();
        imageEntitySet.forEach(img->
                {
                    id.add(img.getId());
                }

        );
        if(this.productRepository.existsById(productDto.getPro_id()))
        {
            ProductEntity product = this.mapper.map(productDto, ProductEntity.class);
            this.productRepository.save(product);
         for(int i=0;i<id.size();i++)
         {
             this.imageRepository.deleteById(id.get(i));
         }
        }
        else
        {
            throw new NoSuchElementException();
        }

    }
}
