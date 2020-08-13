package com.shop.rest.dto.product_order.output;

import com.shop.rest.dto.order.output.OrderWithUserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOrderWithOrderWithUserDTO {
  private Long id;
  private OrderWithUserDTO order;
  private Integer quantity;
}
