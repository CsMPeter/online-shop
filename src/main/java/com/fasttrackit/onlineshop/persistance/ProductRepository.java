package com.fasttrackit.onlineshop.persistance;

import com.fasttrackit.onlineshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
