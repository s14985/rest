package com.shop.rest.dto.product_order;

import com.shop.rest.dto.order.OrderWithUserDTO;
import com.shop.rest.dto.product.output.ProductDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatedProductOrderDTO {
  private Long id;
  private OrderWithUserDTO order;
  private ProductDTO product;
  private Integer quantity;
}
