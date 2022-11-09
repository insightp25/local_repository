package com.athens.week04.controller;

import com.athens.week04.domain.Product;
import com.athens.week04.dto.ProductMyPriceRequestDto;
import com.athens.week04.dto.ProductRequestDto;
import com.athens.week04.repository.ProductRepository;
import com.athens.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductRestContorller {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }
}
