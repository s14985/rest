package com.shop.rest.service;

import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Order;
import com.shop.rest.model.User;
import com.shop.rest.repository.OrderRepository;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  @Override
  public @NotNull Iterable<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order create(
    @NotNull(message = "The order cannot be null.") @Valid Order order
  ) {
    order.setDateCreated(OffsetDateTime.now());
    return orderRepository.save(order);
  }

  @Override
  public Order update(
    @NotNull(message = "The order cannot be null.") @Valid Order order
  ) {
    return orderRepository.save(order);
  }

  @Override
  public List<Order> getUserOrders(User user) {
    return orderRepository.findAllByUser(user.getId());
  }

  @Override
  public Order getOrderById(Long id) {
    return orderRepository
      .findById(id)
      .orElseThrow(
        () -> new ResourceNotFoundException("order", "id", id.toString())
      );
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteById(id);
  }
}
