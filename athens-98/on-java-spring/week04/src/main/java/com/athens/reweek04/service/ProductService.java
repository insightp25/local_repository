package com.athens.reweek04.service;


import com.athens.reweek04.domain.Product;
import com.athens.reweek04.dto.ProductMyPriceRequestDto;
import com.athens.reweek04.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long update(Long id, ProductMyPriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디는 존재하지 않습니다.")
        );
        product.update(requestDto);
        return id;
    }



}
