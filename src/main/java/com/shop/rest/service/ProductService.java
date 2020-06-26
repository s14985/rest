package com.shop.rest.service;

import com.shop.rest.dto.ProductDTO;
import com.shop.rest.model.Product;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductService {
  @NotNull
  Iterable<ProductDTO> getAllProducts();

  ProductDTO getProductById(
    @Min(value = 1L, message = "Invalid product ID.") Long id
  );

  ProductDTO save(ProductDTO product);

  void deleteById(Long id);

  Iterable<ProductDTO> findProductsFromOrdersByProductId(Long id);
}
