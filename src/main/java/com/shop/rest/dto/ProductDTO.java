package com.shop.rest.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {
  private Long id;

  private String name;

  private BigDecimal price;

  private String picture;

  private String details;

  private String manufacturer;

  private String itemCode;

  private String color;

  private String material;
}
