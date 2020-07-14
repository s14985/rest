package com.shop.rest.config.mapper;

import com.shop.rest.dto.order.OrderDTO;
import com.shop.rest.dto.order.OrderWithUserDTO;
import com.shop.rest.dto.order.OrderWithUserWithAddressDTO;
import com.shop.rest.model.Order;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
  Order toModel(OrderWithUserDTO dto);
  OrderWithUserDTO toOrderWithUserDto(Order model);

  List<Order> toModel(List<OrderWithUserDTO> dtos);
  List<OrderWithUserDTO> toOrderWithUserDto(List<Order> models);

  Iterable<Order> toModel(Iterable<OrderWithUserDTO> dtos);
  Iterable<OrderWithUserDTO> toOrderWithUserDto(Iterable<Order> models);

  // NESTING DEGREES MAPPING
  OrderDTO toOrderDto(Order model);

  OrderWithUserWithAddressDTO toOrderWithUserWithAddressDto(Order model);
}
