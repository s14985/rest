package com.shop.rest.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderDTO {
  private List<ProductOrderDTO> productOrders;
}
