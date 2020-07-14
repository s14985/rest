package com.shop.rest.dto.product_order;

import com.shop.rest.dto.order.OrderWithUserWithAddressDTO;
import com.shop.rest.dto.product.FullProductDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FullProductOrderDTO {
  private Long id;
  private FullProductDTO product;
  private OrderWithUserWithAddressDTO order;
  private Integer quantity;
}
