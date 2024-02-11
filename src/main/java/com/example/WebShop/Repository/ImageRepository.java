package com.example.WebShop.Repository;

import com.example.WebShop.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    @Query(value = "delete from image where image.img_id =:id",nativeQuery = true)
    void deleteByIdImg(@Param("id") Long id);
}
