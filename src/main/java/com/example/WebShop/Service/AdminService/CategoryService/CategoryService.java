package com.example.WebShop.Service.AdminService.CategoryService;

import com.example.WebShop.dto.CategoryDto;
import com.example.WebShop.dto.ProductDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    public List<CategoryDto> getAllCategory();
    public CategoryDto addCategory(CategoryDto categoryDto);
    public void deleteCategory(Long id);
//    public Set<ProductDto> GetProductsByCate(Long id);
    public CategoryDto GetALlProductByCate(Long id);
    public void UpdateCategory(CategoryDto categoryDto);
    public CategoryDto GetCategoryById(Long id);
}
