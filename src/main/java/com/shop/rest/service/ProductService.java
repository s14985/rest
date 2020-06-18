package com.shop.rest.service;

import com.shop.rest.model.Product;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductService {
  @NotNull
  Iterable<Product> getAllProducts();

  Product getProductById(
    @Min(value = 1L, message = "Invalid product ID.") Long id
  );

  Product save(Product product);

  void delete(Long id);

  Iterable<Product> findProductsFromOrdersByProductId(Long id);
}
