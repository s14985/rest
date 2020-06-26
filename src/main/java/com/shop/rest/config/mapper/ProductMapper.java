package com.shop.rest.config.mapper;

import com.shop.rest.dto.ProductDTO;
import com.shop.rest.model.Product;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
  Product toModel(ProductDTO dto);
  ProductDTO toDto(Product model);

  List<Product> toModel(List<ProductDTO> dtos);
  List<ProductDTO> toDto(List<Product> models);

  Iterable<Product> toModel(Iterable<ProductDTO> dtos);
  Iterable<ProductDTO> toDto(Iterable<Product> models);
}
