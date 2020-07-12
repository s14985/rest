package com.shop.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ListedProductDTO {
    private Long id;

    private String name;

    private BigDecimal price;

    private String picture;
}
