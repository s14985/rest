package com.shop.rest.dto;

import lombok.Data;

@Data
public class ProductDTO {
  private String name;

  private Double price;

  private String pictureUrl;

  private String details;
}
