package com.shop.rest.service;

import com.shop.rest.config.mapper.OrderMapper;
import com.shop.rest.config.mapper.ProductOrderMapper;
import com.shop.rest.dto.OrderDTO;
import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Order;
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
  private final OrderMapper orderMapper;
  private final ProductOrderMapper productOrderMapper;

  @Override
  public @NotNull Iterable<OrderDTO> getAllOrders() {
    return orderMapper.toDto(orderRepository.findAll());
  }

  @Override
  public OrderDTO create(
    @NotNull(message = "The order cannot be null.") @Valid OrderDTO order
  ) {
    order.setDateCreated(OffsetDateTime.now());
    return orderMapper.toDto(orderRepository.save(orderMapper.toModel(order)));
  }

  @Override
  public OrderDTO update(
    @NotNull(message = "The order cannot be null.") @Valid OrderDTO order
  ) {
    return orderMapper.toDto(orderRepository.save(orderMapper.toModel(order)));
  }

  @Override
  public OrderDTO setProductOrders(
    OrderDTO orderDto,
    List<ProductOrderDTO> productOrders
  ) {
    Order o = orderMapper.toModel(orderDto);
    o.setProductOrders(productOrderMapper.toModel(productOrders));
    return orderMapper.toDto(o);
  }

  @Override
  public OrderDTO getOrderById(Long id) {
    return orderMapper.toDto(
      orderRepository
        .findById(id)
        .orElseThrow(
          () -> new ResourceNotFoundException("order", "id", id.toString())
        )
    );
  }
}
