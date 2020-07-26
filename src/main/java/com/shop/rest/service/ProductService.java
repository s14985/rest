package com.shop.rest.service;

import com.shop.rest.dto.product.output.*;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductService {
  @NotNull
  Iterable<ProductDTO> getProducts();

  ProductDTO save(ProductDTO product);

  void deleteById(Long id);

  Iterable<ProductDTO> getSuggestedProductsFromOrdersByProductId(Long id);

  ProductDTO getProductById(Long id);

  ProductWithProductOrdersDTO getProductWithProductOrdersById(Long id);

  ProductWithProductOrdersWithOrderDTO getProductWithProductOrdersWithOrderById(
    Long id
  );

  ProductWithProductOrdersWithOrderWithUserDTO getProductWithProductOrdersWithOrderWithUserById(
    Long id
  );

  ProductWithProductOrdersWithOrderWithUserWithAddressDTO getProductWithProductOrdersWithOrderWithUserWithAddressById(
    Long id
  );
}
