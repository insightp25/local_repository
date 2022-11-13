package com.athens.reweek04.controller;

import com.athens.reweek04.domain.Product;
import com.athens.reweek04.dto.ProductMyPriceRequestDto;
import com.athens.reweek04.dto.ProductRequestDto;
import com.athens.reweek04.repository.ProductRepository;
import com.athens.reweek04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;
    private final ProductRepository productRepository;


    @GetMapping("/api/products")
    public List<Product> getProucts() {
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
