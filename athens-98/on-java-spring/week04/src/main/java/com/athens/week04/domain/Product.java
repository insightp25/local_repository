package com.athens.week04.domain;

import com.athens.week04.dto.ProductMyPriceRequestDto;
import com.athens.week04.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myPrice;

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myPrice = 0;
    }

    public void update(ProductMyPriceRequestDto requestDto) {
        this.myPrice = requestDto.getMyPrice();
    }

}
