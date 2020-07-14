package com.shop.rest.dto.product_order;

import java.util.List;
import lombok.Data;

@Data
public class OrderedProductOrdersListDTO {
  private List<OrderedProductOrderDTO> productOrders;
}
