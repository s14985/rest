package com.shop.rest.config.mapper;

import com.shop.rest.dto.product.FullProductDTO;
import com.shop.rest.dto.product.output.*;
import com.shop.rest.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
  Product toModel(ProductDTO dto);

  Iterable<ProductDTO> toProductDto(Iterable<Product> models);

  FullProductDTO toFullProductDto(Product model);

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
}
