package com.shop.rest.controller;

import com.shop.rest.dto.OrderDTO;
import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.dto.ProductOrdersDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Status;
import com.shop.rest.service.OrderService;
import com.shop.rest.service.ProductOrderService;
import com.shop.rest.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
  private final OrderService orderService;
  private final ProductService productService;
  private final ProductOrderService productOrderService;

  @GetMapping
  public ResponseEntity<Iterable<OrderDTO>> list() {
    return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderDTO> createOrder(
    @RequestBody ProductOrdersDTO productOrdersDTO
  ) {
    List<ProductOrderDTO> productOrderDTOS = productOrdersDTO.getProductOrders();
    validateProductsExistence(productOrderDTOS);
    OrderDTO order = OrderDTO.builder().status(Status.CREATED).build();
    order = orderService.create(order);

    List<ProductOrderDTO> productOrders = new ArrayList<>();
    for (ProductOrderDTO dto : productOrderDTOS) {
      productOrders.add(
        productOrderService.create(
          ProductOrderDTO
            .builder()
            .order(order)
            .quantity(dto.getQuantity())
            .product(productService.getProductById(dto.getProduct().getId()))
            .build()
        )
      );
    }

    orderService.update(orderService.setProductOrders(order, productOrders));

    String uri = ServletUriComponentsBuilder
      .fromCurrentServletMapping()
      .path("/orders/{id}")
      .buildAndExpand(order.getId())
      .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<OrderDTO> finishOrder(@RequestBody Long id) {
    OrderDTO orderDTO = orderService.getOrderById(id);
    orderDTO.setStatus(Status.FINISHED);
    orderService.update(orderDTO);
    return new ResponseEntity<>(orderDTO, HttpStatus.OK);
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
