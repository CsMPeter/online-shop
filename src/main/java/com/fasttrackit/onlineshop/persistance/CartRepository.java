package com.fasttrackit.onlineshop.persistance;

import com.fasttrackit.onlineshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>{


}
