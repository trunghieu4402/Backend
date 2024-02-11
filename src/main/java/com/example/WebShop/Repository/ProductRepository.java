package com.example.WebShop.Repository;

import com.example.WebShop.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query(value = "SELECT * FROM db_ecommer.product where pro_name like %:value%" , nativeQuery = true)
    List<ProductEntity> FindProductEntityByName( @Param("value") String value);
//    Set<ProductEntity> FindProductEntityByCategoryEntities(Long id);
    @Query(value = "SELECT * FROM product where product.cate_id =:id", nativeQuery = true)
    List<ProductEntity> findALLByCategory(@Param("id") Long id);
    @Query(value = "SELECT * FROM db_ecommer.product where pro_name like %:value% and cate_id=:id" , nativeQuery = true)
    List<ProductEntity> FindProductEntityByName( @Param("value") String value, @Param("id") Long id);

}
