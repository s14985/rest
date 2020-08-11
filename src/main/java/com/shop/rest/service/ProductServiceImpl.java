package com.shop.rest.service;

import com.shop.rest.config.mapper.ProductMapper;
import com.shop.rest.dto.product.output.*;
import com.shop.rest.dto.product_order.SimpleProductOrderDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Product;
import com.shop.rest.repository.ProductRepository;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductOrderService productOrderService;
  private final ProductMapper productMapper;

  private Product getModel(
    @Min(value = 1L, message = "Invalid product ID.") Long id
  ) {
    return productRepository
      .findById(id)
      .orElseThrow(
        () -> new ResourceNotFoundException("product", "id", id.toString())
      );
  }

  @Override
  public Iterable<ProductDTO> getProducts() {
    return productMapper.toProductDto(productRepository.findAll());
  }

  @Override
  public ProductDTO save(ProductDTO product) {
    return productMapper.toProductDto(
      productRepository.save(productMapper.toModel(product))
    );
  }

  @Override
  public ProductDTO update(ProductDTO product) {
    return productMapper.toProductDto(
            productRepository.save(productMapper.updateProduct(product, getModel(product.getId()))));
  }

  @Override
  public void deleteById(Long id) {
    productOrderService.deleteAll(
      productOrderService.getSimpleProductOrdersByProductId(id)
    );
    productRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Iterable<ProductDTO> getSuggestedProductsFromOrdersByProductId(
    Long id
  ) {
    return productOrderService
      .getSimpleProductOrderByOrdersIdIn(
        productOrderService
          .getSimpleProductOrdersByProductId(id)
          .stream()
          .map(productOrderDTO -> productOrderDTO.getOrder().getId())
          .collect(Collectors.toList())
      )
      .stream()
      .map(SimpleProductOrderDTO::getProduct)
      .collect(Collectors.toList());
  }

  /**
   * 0 degree nesting
   */
  @Override
  public ProductDTO getProductById(Long id) {
    return productMapper.toProductDto(getModel(id));
  }

  /**
   * 1 degree nesting
   */
  @Override
  public ProductWithProductOrdersDTO getProductWithProductOrdersById(Long id) {
    return productMapper.toProductWithProductOrdersDto(getModel(id));
  }

  /**
   * 2 degree nesting
   */
  @Override
  public ProductWithProductOrdersWithOrderDTO getProductWithProductOrdersWithOrderById(
    Long id
  ) {
    ProductWithProductOrdersWithOrderDTO product = productMapper.toProductWithProductOrdersWithOrderDto(
      getModel(id)
    );
    product.setProductOrders(
      productOrderService.getProductOrderWithOrderByProductId(id)
    );
    return product;
  }

  /**
   * 3 degree nesting
   */
  @Override
  public ProductWithProductOrdersWithOrderWithUserDTO getProductWithProductOrdersWithOrderWithUserById(
    Long id
  ) {
    ProductWithProductOrdersWithOrderWithUserDTO product = productMapper.toProductWithProductOrdersWithOrderWithUserDto(
      getModel(id)
    );
    product.setProductOrders(
      productOrderService.getProductOrdersWithOrderWithUserByProductId(id)
    );
    return product;
  }

  /**
   * 4 degree nesting
   */
  @Override
  public ProductWithProductOrdersWithOrderWithUserWithAddressDTO getProductWithProductOrdersWithOrderWithUserWithAddressById(
    Long id
  ) {
    ProductWithProductOrdersWithOrderWithUserWithAddressDTO product = productMapper.toProductWithProductOrdersWithOrderWithUserWithAddressDto(
      getModel(id)
    );
    product.setProductOrders(
      productOrderService.getProductOrderWithOrderWithUserWithAddressByProductId(
        id
      )
    );
    return product;
  }
}
