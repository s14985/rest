package com.shop.rest.config.mapper;

import com.shop.rest.dto.order.OrderWithUserDTO;
import com.shop.rest.model.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
  Order toModel(OrderWithUserDTO dto);

  OrderWithUserDTO toOrderWithUserDto(Order model);
}
