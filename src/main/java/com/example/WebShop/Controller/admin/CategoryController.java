package com.example.WebShop.Controller.admin;

import com.example.WebShop.Service.AdminService.CategoryService.CategoryService;
import com.example.WebShop.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("admin/Category")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    @GetMapping("")
    public List<CategoryDto> getCategory()
    {
        return this.categoryService.getAllCategory();
    }
    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto)
    {
        return ResponseEntity.ok(this.categoryService.addCategory(categoryDto));
    }
    @DeleteMapping(value = "/deleteCategory")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCategory(@RequestParam("id") Long id) {
        System.out.println(id);
       return this.categoryService.deleteCategory(id);
//        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto)
    {
        this.categoryService.UpdateCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/GetCategoryById")
    public ResponseEntity<?> categoryDto(@RequestParam("id") Long id)
    {
        return this.categoryService.GetCategoryById(id);
    }

//    @GetMapping(value = "/getProductsByCateID ")
//    public ResponseEntity<?> getProductsByCateID(@RequestParam("id") Long id)
//    {
//        return this.categoryService.getProductsByCateID(id);
//    }
}
