package com.shop.rest.service;

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
  Iterable<Order> getAllOrders();

  Order create(
    @NotNull(message = "The order cannot be null.") @Valid Order order
  );

  Order update(
    @NotNull(message = "The order cannot be null.") @Valid Order order
  );

  List<Order> getUserOrders(User user);

  Order getOrderById(@Min(value = 1L, message = "Invalid order ID.") Long id);

  void delete(Long id);
}
