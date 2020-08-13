package com.shop.rest.controller;

import com.shop.rest.dto.order.output.OrderWithUserDTO;
import com.shop.rest.dto.product_order.*;
import com.shop.rest.model.Status;
import com.shop.rest.service.*;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;
  private final ProductService productService;
  private final ProductOrderService productOrderService;
  private final UserService userService;

  @PostMapping(
    value = "",
    consumes = { MediaType.APPLICATION_JSON_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE }
  )
  public ResponseEntity<OrderWithUserDTO> createOrder(
    @RequestBody OrderedProductOrdersListDTO orderedProductOrdersList
  ) {
    List<OrderedProductOrderDTO> orderedProductOrders = orderedProductOrdersList.getProductOrders();
    OrderWithUserDTO order = OrderWithUserDTO
      .builder()
      .status(Status.CREATED)
      .user(
        userService.getUserById(new RandomDataGenerator().nextLong(1L, 10000L))
      )
      .build();
    order = orderService.create(order);

    List<CreatedProductOrderDTO> productOrders = new ArrayList<>();
    for (OrderedProductOrderDTO dto : orderedProductOrders) {
      productOrders.add(
        productOrderService.create(
          CreatedProductOrderDTO
            .builder()
            .order(order)
            .quantity(dto.getQuantity())
            .product(productService.getProductById(dto.getProduct().getId()))
            .build()
        )
      );
    }

    orderService.update(orderService.setOrder(order, productOrders));

    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  @PutMapping(
    value = "",
    consumes = { MediaType.APPLICATION_JSON_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE }
  )
  public ResponseEntity<OrderWithUserDTO> finishOrder(@RequestBody Long id) {
    OrderWithUserDTO orderWithUser = orderService.getOrderWithUserById(id);
    orderWithUser.setStatus(Status.FINISHED);
    orderService.update(orderWithUser);
    return new ResponseEntity<>(orderWithUser, HttpStatus.OK);
  }
}
