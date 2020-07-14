package com.shop.rest.dto.product_order;

import com.shop.rest.dto.product.output.ProductDTO;
import lombok.Data;

@Data
public class OrderedProductOrderDTO {
  private ProductDTO product;
  private Integer quantity;
}
