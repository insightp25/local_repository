package com.athens.reweek04.repository;

import com.athens.reweek04.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
