package com.fasttrackit.onlineshop.persistance;

import com.fasttrackit.onlineshop.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    query by nested properties
    Page<Review> findByProductId(Long productId, Pageable pageable);
}
