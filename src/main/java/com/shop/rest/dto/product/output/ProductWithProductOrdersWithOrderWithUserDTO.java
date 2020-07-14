package com.shop.rest.dto.product.output;

import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserDTO;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProductWithProductOrdersWithOrderWithUserDTO {
  private Long id;

  private String name;

  private BigDecimal price;

  private String picture;

  private String details;

  private String manufacturer;

  private String itemCode;

  private String color;

  private String material;

  private List<ProductOrderWithOrderWithUserDTO> productOrders;
}
