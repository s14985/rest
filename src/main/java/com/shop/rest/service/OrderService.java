package com.shop.rest.service;

import com.shop.rest.dto.OrderDTO;
import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.model.Order;
import com.shop.rest.model.User;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderService {
  @NotNull
  Iterable<OrderDTO> getAllOrders();

  OrderDTO create(
    @NotNull(message = "The order cannot be null.") @Valid OrderDTO order
  );

  OrderDTO update(
    @NotNull(message = "The order cannot be null.") @Valid OrderDTO order
  );

  OrderDTO setProductOrders(
    OrderDTO orderDto,
    List<ProductOrderDTO> productOrders
  );
}
