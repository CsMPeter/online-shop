package com.fasttrackit.onlineshop.persistance;

import com.fasttrackit.onlineshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> finByNameContainingAndQuantityGreaterThanEqual(String partialName, int minQuantity, Pageable pageable);

//    JPQL syntax (java persistence query language)
//    @Query("SELECT product FROM Product product WHERE name LIKE '%:partialName%'")
    @Query(value = "SELECT * FROM product where `name` LIKE '%?0%'", nativeQuery = true)
    Page<Product> findByPartialName(String partialName,Pageable pageable);


}
