package com.shop.rest.config.mapper;

import com.shop.rest.dto.product.FullProductDTO;
import com.shop.rest.dto.product.output.*;
import com.shop.rest.model.Product;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
  Product toModel(ProductDTO dto);
  ProductDTO toProductDto(Product model);

  List<Product> toModel(List<ProductDTO> dtos);
  List<ProductDTO> toProductDto(List<Product> models);

  Iterable<Product> toModel(Iterable<ProductDTO> dtos);
  Iterable<ProductDTO> toProductDto(Iterable<Product> models);

  FullProductDTO toFullProductDto(Product model);

  // NESTING DEGREES MAPPING
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
