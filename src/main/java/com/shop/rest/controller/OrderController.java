package com.shop.rest.controller;

import com.shop.rest.dto.OrderDTO;
import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Order;
import com.shop.rest.model.ProductOrder;
import com.shop.rest.model.Status;
import com.shop.rest.service.OrderService;
import com.shop.rest.service.ProductOrderService;
import com.shop.rest.service.ProductService;
import com.shop.rest.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;
  private final ProductService productService;
  private final UserService userService;
  private final ProductOrderService productOrderService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public @NotNull Iterable<Order> list() {
    return orderService.getAllOrders();
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody OrderDTO orderDTO) {
    List<ProductOrderDTO> productOrderDTOS = orderDTO.getProductOrders();
    validateProductsExistence(productOrderDTOS);
    Order order = new Order();
    order.setStatus(Status.CREATED);
    order.setUser(userService.getCurrentUser());
    order = this.orderService.create(order);

    List<ProductOrder> productOrders = new ArrayList<>();
    for (ProductOrderDTO dto : productOrderDTOS) {
      productOrders.add(
        productOrderService.create(
          new ProductOrder(
            order,
            productService.getProductById(dto.getProduct().getId()),
            dto.getQuantity()
          )
        )
      );
    }

    order.setProductOrders(productOrders);

    this.orderService.update(order);

    String uri = ServletUriComponentsBuilder
      .fromCurrentServletMapping()
      .path("/orders/{id}")
      .buildAndExpand(order.getId())
      .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
  }

  private void validateProductsExistence(List<ProductOrderDTO> productOrders) {
    List<ProductOrderDTO> list = productOrders
      .stream()
      .filter(
        op ->
          Objects.isNull(productService.getProductById(op.getProduct().getId()))
      )
      .collect(Collectors.toList());

    if (!CollectionUtils.isEmpty(list)) {
      throw new ResourceNotFoundException("Product not found");
    }
  }
}
