package com.shop.rest.config.mapper;

import com.shop.rest.dto.product_order.*;
import com.shop.rest.dto.product_order.output.ProductOrderDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserWithAddressDTO;
import com.shop.rest.model.ProductOrder;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductOrderMapper {
  ProductOrder fullProductOrderToModel(FullProductOrderDTO dto);
  FullProductOrderDTO toFullProductOrderDto(ProductOrder model);

  List<ProductOrder> fullProductOrderToModel(List<FullProductOrderDTO> dtos);
  List<FullProductOrderDTO> toFullProductOrderDto(List<ProductOrder> models);

  Iterable<ProductOrder> fullProductOrderToModel(
    Iterable<FullProductOrderDTO> dtos
  );
  Iterable<FullProductOrderDTO> toFullProductOrderDto(
    Iterable<ProductOrder> models
  );

  List<SuggestedProductOrderDTO> toSuggestedProductOrderDto(
    List<ProductOrder> model
  );

  ProductOrder createdProductOrderToModel(CreatedProductOrderDTO dto);
  CreatedProductOrderDTO toCreatedProductOrderDto(ProductOrder model);

  List<ProductOrder> createdProductOrderToModel(
    List<CreatedProductOrderDTO> dtos
  );

  // NESTING DEGREES MAPPING
  List<ProductOrderDTO> toProductOrderDto(List<ProductOrder> models);

  List<ProductOrderWithOrderDTO> toProductOrderWithOrderDto(
    List<ProductOrder> models
  );

  List<ProductOrderWithOrderWithUserDTO> toProductOrderWithOrderWithUserDto(
    List<ProductOrder> models
  );

  List<ProductOrderWithOrderWithUserWithAddressDTO> toProductOrderWithOrderWithUserWithAddressDto(
    List<ProductOrder> models
  );
}
