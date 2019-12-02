package com.fasttrackit.onlineshop.persistance;

import com.fasttrackit.onlineshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> finByNameContainingAndQuantityGreaterThanEqual(String partialName, int minQuantity, Pageable pageable);


}
