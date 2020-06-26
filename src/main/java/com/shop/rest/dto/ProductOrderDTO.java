package com.shop.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOrderDTO {
  private Long id;
  private ProductDTO product;
  private OrderDTO order;
  private Integer quantity;
}
