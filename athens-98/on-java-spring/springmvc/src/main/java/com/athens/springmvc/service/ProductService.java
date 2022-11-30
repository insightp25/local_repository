package com.athens.springmvc.service;

import com.athens.springmvc.dto.ProductMypriceRequestDto;
import com.athens.springmvc.dto.ProductRequestDto;
import com.athens.springmvc.entity.Product;
import com.athens.springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts(Long userId) {
        return productRepository.findAllById(userId);
    }


    public Product createProduct(ProductRequestDto requestDto, Long userId) {
        Product product = new Product(requestDto, userId);

        productRepository.save(product);

        return product;
    }


    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
