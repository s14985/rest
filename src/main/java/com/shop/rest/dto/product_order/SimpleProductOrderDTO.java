package com.shop.rest.dto.product_order;

import com.shop.rest.dto.order.output.OrderDTO;
import com.shop.rest.dto.product.output.ProductDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleProductOrderDTO {
  private Long id;
  private ProductDTO product;
  private OrderDTO order;
  private Integer quantity;
}
