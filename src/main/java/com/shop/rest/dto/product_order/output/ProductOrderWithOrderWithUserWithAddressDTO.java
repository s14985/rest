package com.shop.rest.dto.product_order.output;

import com.shop.rest.dto.order.OrderWithUserWithAddressDTO;
import lombok.Data;

@Data
public class ProductOrderWithOrderWithUserWithAddressDTO {
  private Long id;
  private OrderWithUserWithAddressDTO order;
  private Integer quantity;
}
