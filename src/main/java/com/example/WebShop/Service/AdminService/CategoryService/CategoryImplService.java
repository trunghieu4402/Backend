package com.example.WebShop.Service.AdminService.CategoryService;

import com.example.WebShop.Entity.CategoryEntity;
import com.example.WebShop.Entity.ProductEntity;
import com.example.WebShop.Repository.CategoryRepository;
import com.example.WebShop.Repository.ProductRepository;
import com.example.WebShop.dto.CategoryDto;
import com.example.WebShop.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryImplService implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    private CategoryEntity categoryentity;
    private final ModelMapper mapper= new ModelMapper();
    public List<CategoryDto>getAllCategory()
    {
        List<CategoryEntity> list = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategoryEntity s : list)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDtos.add(categoryDto = this.mapper.map(s,CategoryDto.class));
        }
        return  categoryDtos;

    }

    public CategoryDto addCategory(CategoryDto categoryDto)
    {
        CategoryEntity category = this.mapper.map(categoryDto,CategoryEntity.class);
        category=this.categoryRepository.save(category);
        return mapper.map(category,CategoryDto.class);

    }
    public ResponseEntity deleteCategory(Long id)
    {
        if(this.categoryRepository.findById(id).isEmpty())
        {
            return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
        }
        else
        {
            List<ProductEntity> list= this.productRepository.findALLByCategory(id);
            if(list!=null)
            {
//                this.categoryentity=this.mapper.map(GetALlProductByCate(id),CategoryEntity.class);
//                System.out.println(this.categoryentity);
                list.forEach(i->
                {
                    this.productRepository.deleteById(i.getPro_id());
                });
                this.categoryRepository.deleteById(id);
                return new ResponseEntity<>("Delete Complete", HttpStatus.OK);
            }
            else
            {
                this.categoryRepository.deleteById(id);
                return new ResponseEntity<>("Delete Complete", HttpStatus.OK);
            }
        }
    }
    public void UpdateCategory(CategoryDto categoryDto)
    {
        if(this.categoryRepository.existsById(categoryDto.getCate_id()))
        {
            CategoryEntity category = this.mapper.map(categoryDto,CategoryEntity.class);
            this.categoryRepository.save(category);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
    public CategoryDto GetALlProductByCate(Long id)
    {
        CategoryEntity category = this.categoryRepository.findById(id).get();
        CategoryDto categoryDto = this.mapper.map(category,CategoryDto.class);
        categoryDto.setProducts(FindAllProductsByCate(id));
        return categoryDto;
    }
    public Set<ProductDto> FindAllProductsByCate(Long id)
    {
            List<ProductEntity> list = new ArrayList<>();
            Set<ProductDto> ListDtos= new HashSet<>();
            list= this.productRepository.findALLByCategory(id);
        System.out.println(list.size());
                list.forEach(i->
                {
                    ProductDto productDto= this.mapper.map(i,ProductDto.class);
                    ListDtos.add(productDto);
                });
                return ListDtos;

    }
    public ResponseEntity GetCategoryById(Long id)
    {
        if(this.categoryRepository.findById(id).isEmpty())
        {
            return new ResponseEntity<>("Category Not Found",HttpStatus.NOT_FOUND);
        }
        else
        {
            CategoryDto categoryDto = this.mapper.map(this.categoryRepository.findById(id).get(),CategoryDto.class);

            return new ResponseEntity<>(categoryDto,HttpStatus.OK);
        }
    }
    public ResponseEntity<?> getProductsByCateID(Long id)
    {
        if(this.categoryRepository.findById(id).isEmpty())
        {
            return new ResponseEntity<>("Category Not Found",HttpStatus.NOT_FOUND);
        }
        else
        {
            CategoryDto categoryDto = this.GetALlProductByCate(id);
            categoryDto.setProducts(this.FindAllProductsByCate(id));
            System.out.println(this.GetALlProductByCate(id).getProducts().size());
            return new ResponseEntity<>(categoryDto,HttpStatus.OK);
        }
    }
}
