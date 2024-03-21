package com.example.WebShop.Repository;

import com.example.WebShop.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
@Query(value = "SELECT * FROM db_ecommer.address where user_id=:userid and id=:id",nativeQuery = true)
    Optional<Address> findAddressByIdAndUserId(@Param("userid") Long Userid, @Param("id") Long id);
    @Query(value = "SELECT * FROM db_ecommer.address where user_id=:userid",nativeQuery = true)
    List<Address> findAllByUserId(@Param("userid") Long id);
}
