package com.shop.rest.service;

import com.shop.rest.config.mapper.ProductMapper;
import com.shop.rest.dto.ProductDTO;
import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.repository.ProductRepository;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductOrderService productOrderService;
  private final ProductMapper productMapper;

  @Override
  public Iterable<ProductDTO> getAllProducts() {
    return productMapper.toDto(productRepository.findAll());
  }

  @Override
  public ProductDTO getProductById(
    @Min(value = 1L, message = "Invalid product ID.") Long id
  ) {
    return productMapper.toDto(
      productRepository
        .findById(id)
        .orElseThrow(
          () -> new ResourceNotFoundException("product", "id", id.toString())
        )
    );
  }

  @Override
  public ProductDTO save(ProductDTO product) {
    return productMapper.toDto(
      productRepository.save(productMapper.toModel(product))
    );
  }

  @Override
  public void deleteById(Long id) {
    productOrderService.deleteAll(productOrderService.getAllByProductId(id));
    productRepository.deleteById(id);
  }

  @Override
  public Iterable<ProductDTO> findProductsFromOrdersByProductId(Long id) {
    return productOrderService
      .getAllByOrder_IdIn(
        productOrderService
          .getAllByProductId(id)
          .stream()
          .map(productOrderDTO -> productOrderDTO.getOrder().getId())
          .collect(Collectors.toList())
      )
      .stream()
      .map(ProductOrderDTO::getProduct)
      .distinct()
      .collect(Collectors.toList());
  }
}
