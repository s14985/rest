package com.shop.rest.service;

import com.shop.rest.dto.ListedProductDTO;
import com.shop.rest.dto.ProductDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.shop.rest.dto.ProductWithProductOrdersDTO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductService {
  @NotNull
  Iterable<ProductDTO> getAllProducts();

  @NotNull
  Iterable<ListedProductDTO> getListedProducts();

  ProductDTO getProductById(
    @Min(value = 1L, message = "Invalid product ID.") Long id
  );

  ProductDTO save(ProductDTO product);

  void deleteById(Long id);

  Iterable<ProductDTO> getProductsFromOrdersByProductId(Long id);

  ProductWithProductOrdersDTO getFullProduct(Long id);
}
