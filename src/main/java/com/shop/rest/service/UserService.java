package com.shop.rest.service;

import com.shop.rest.model.Order;
import com.shop.rest.model.User;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
  @NotNull
  Iterable<User> getAllUsers();

  User getUserById(@Min(value = 1L, message = "Invalid user ID.") Long id);

  User save(User user);

  User getCurrentUser();

  User getUserByOrderInOrders(Order order);
}
