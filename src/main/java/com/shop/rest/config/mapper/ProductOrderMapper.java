package com.shop.rest.config.mapper;

import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.model.ProductOrder;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductOrderMapper {
  ProductOrder toModel(ProductOrderDTO dto);
  ProductOrderDTO toDto(ProductOrder model);

  List<ProductOrder> toModel(List<ProductOrderDTO> dtos);
  List<ProductOrderDTO> toDto(List<ProductOrder> models);

  Iterable<ProductOrder> toModel(Iterable<ProductOrderDTO> dtos);
  Iterable<ProductOrderDTO> toDto(Iterable<ProductOrder> models);
}
