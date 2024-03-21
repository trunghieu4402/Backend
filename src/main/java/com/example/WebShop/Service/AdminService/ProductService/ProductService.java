package com.example.WebShop.Service.AdminService.ProductService;

import com.example.WebShop.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto searchProductByID(long id);

    public ResponseEntity<?>SearchProduct(String value, Long id);

    public ResponseEntity<?> DeleteProduct(Long id);
    public void UpdateProducts(ProductDto productDto);
}
