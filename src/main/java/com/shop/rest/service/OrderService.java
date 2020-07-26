package com.shop.rest.service;

import com.shop.rest.dto.order.OrderWithUserDTO;
import com.shop.rest.dto.product_order.CreatedProductOrderDTO;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderService {
  OrderWithUserDTO create(
    @NotNull(
      message = "The order cannot be null."
    ) @Valid OrderWithUserDTO order
  );

  OrderWithUserDTO update(
    @NotNull(
      message = "The order cannot be null."
    ) @Valid OrderWithUserDTO order
  );

  OrderWithUserDTO setOrder(
    OrderWithUserDTO orderWithUser,
    List<CreatedProductOrderDTO> productOrders
  );

  OrderWithUserDTO getOrderWithUserById(Long id);
}
