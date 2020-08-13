package com.shop.rest.config.mapper;

import com.shop.rest.dto.product.output.*;
import com.shop.rest.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {
  Product toModel(ProductDTO dto);

  Iterable<ProductDTO> toProductDto(Iterable<Product> models);

  ProductDTO toProductDto(Product model);

  ProductWithProductOrdersDTO toProductWithProductOrdersDto(Product model);

  ProductWithProductOrdersWithOrderDTO toProductWithProductOrdersWithOrderDto(
    Product model
  );

  ProductWithProductOrdersWithOrderWithUserDTO toProductWithProductOrdersWithOrderWithUserDto(
    Product model
  );

  ProductWithProductOrdersWithOrderWithUserWithAddressDTO toProductWithProductOrdersWithOrderWithUserWithAddressDto(
    Product model
  );

  @Mapping(target = "id", ignore = true)
  Product updateProduct(ProductDTO dto, @MappingTarget Product model);
}
