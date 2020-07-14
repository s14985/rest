package com.shop.rest.dto.product.output;

import java.math.BigDecimal;
import java.util.List;

import com.shop.rest.dto.product_order.output.ProductOrderWithOrderDTO;
import lombok.Data;

@Data
public class ProductWithProductOrdersWithOrderDTO {
  private Long id;

  private String name;

  private BigDecimal price;

  private String picture;

  private String details;

  private String manufacturer;

  private String itemCode;

  private String color;

  private String material;

  private List<ProductOrderWithOrderDTO> productOrders;
}
