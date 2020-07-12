package com.shop.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailsDTO {
  private ProductDTO product;
  private Iterable<ProductDTO> suggestedProducts;
}
