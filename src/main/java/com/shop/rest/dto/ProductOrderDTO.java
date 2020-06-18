package com.shop.rest.dto;

import com.shop.rest.model.Product;
import lombok.Data;

@Data
public class ProductOrderDTO {
  private Product product;
  private Integer quantity;
}
