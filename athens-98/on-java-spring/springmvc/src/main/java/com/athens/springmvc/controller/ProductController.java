package com.athens.springmvc.controller;

import com.athens.springmvc.dto.ProductMypriceRequestDto;
import com.athens.springmvc.dto.ProductRequestDto;
import com.athens.springmvc.entity.Product;
import com.athens.springmvc.service.ProductService;
import com.athens.springmvc.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

     // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws SQLException {
        // login 되어있는 회원 테이블의 id
        Long userId = userDetails.getUser().getId();

        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = productService.createProduct(requestDto, userId);

        // 응답 보내기
        return product;
    }


    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productService.updateProduct(id, requestDto);

        return product.getId();
    }


    // login한 회원이 등록한 관심 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // login 되어있는 회원 테이블의 id
        Long userId = userDetails.getUser().getId();

        return productService.getProducts(userId);
    }


}