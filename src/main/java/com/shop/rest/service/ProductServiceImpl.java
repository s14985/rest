package com.shop.rest.service;

import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Product;
import com.shop.rest.repository.ProductRepository;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public @NotNull Iterable<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(
    @Min(value = 1L, message = "Invalid product ID.") Long id
  ) {
    return productRepository
      .findById(id)
      .orElseThrow(
        () -> new ResourceNotFoundException("product", "id", id.toString())
      );
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public Iterable<Product> findProductsFromOrdersByProductId(Long id) {
    return productRepository.findProductsFromOrdersByProductId(id);
  }
}
