package com.shop.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductWithProductOrdersDTO {
    private Long id;

    private String name;

    private BigDecimal price;

    private String picture;

    private String details;

    private String manufacturer;

    private String itemCode;

    private String color;

    private String material;

    private List<ProductOrderDTO> productOrder;
}
