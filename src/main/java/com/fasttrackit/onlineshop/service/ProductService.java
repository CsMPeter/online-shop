package com.fasttrackit.onlineshop.service;

import com.fasttrackit.onlineshop.domain.Product;
import com.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import com.fasttrackit.onlineshop.persistance.ProductRepository;
import com.fasttrackit.onlineshop.transfer.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    //ioc-inversion of control
    private final ProductRepository productRepository;

    //Dependency injection
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}",request);
        Product product = new Product();
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);

    }

    public Product getProduct(long id)  {
        LOGGER.info("Retrieving product {]", id);

//        using optional
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "does not exist."));
    }

    public Product updateProduct(long id, SaveProductRequest request)  {
        LOGGER.info("Updating product {}: {}", id, request);

        Product product = getProduct(id);
        BeanUtils.copyProperties(request,product);

        return productRepository.save(product);

    }

    public void deleteProduct(long id){
        LOGGER.info("Deleting product {}",  id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);

    }
}
