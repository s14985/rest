package com.shop.rest.config.mapper;

import com.shop.rest.dto.OrderDTO;
import com.shop.rest.model.Order;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
  Order toModel(OrderDTO dto);
  OrderDTO toDto(Order model);

  List<Order> toModel(List<OrderDTO> dtos);
  List<OrderDTO> toDto(List<Order> models);

  Iterable<Order> toModel(Iterable<OrderDTO> dtos);
  Iterable<OrderDTO> toDto(Iterable<Order> models);
}
