package com.shop.rest.service;

import com.shop.rest.config.mapper.ProductOrderMapper;
import com.shop.rest.dto.product_order.*;
import com.shop.rest.dto.product_order.output.ProductOrderDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserWithAddressDTO;
import com.shop.rest.model.ProductOrder;
import com.shop.rest.repository.ProductOrderRepository;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
  private final ProductOrderRepository productOrderRepository;
  private final ProductOrderMapper productOrderMapper;

  private List<ProductOrder> getModelsByProductId(Long id) {
    return productOrderRepository.findAllByProduct_Id(id);
  }

  @Override
  public CreatedProductOrderDTO create(
    @NotNull(
      message = "The products for order cannot be null."
    ) @Valid CreatedProductOrderDTO productOrder
  ) {
    return productOrderMapper.toCreatedProductOrderDto(
      productOrderRepository.save(
        productOrderMapper.createdProductOrderToModel(productOrder)
      )
    );
  }

  @Override
  public List<FullProductOrderDTO> getFullProductOrdersByProductId(Long id) {
    return productOrderMapper.toFullProductOrderDto(getModelsByProductId(id));
  }

  public void deleteAll(List<FullProductOrderDTO> productOrders) {
    productOrderRepository.deleteAll(
      productOrderMapper.fullProductOrderToModel(productOrders)
    );
  }

  @Override
  public List<SuggestedProductOrderDTO> getSuggestedProductOrderByOrdersIdIn(
    List<Long> ids
  ) {
    return productOrderMapper.toSuggestedProductOrderDto(
      productOrderRepository.findAllByOrdersIdIn(ids)
    );
  }

  @Override
  public List<SuggestedProductOrderDTO> getSuggestedProductOrdersByProductId(
    Long id
  ) {
    return productOrderMapper.toSuggestedProductOrderDto(
      getModelsByProductId(id)
    );
  }

  /**
   * 1 degree nesting
   */
  @Override
  public List<ProductOrderDTO> getProductOrdersByProductId(Long id) {
    return productOrderMapper.toProductOrderDto(getModelsByProductId(id));
  }

  /**
   * 2 degree nesting
   */
  @Override
  public List<ProductOrderWithOrderDTO> getProductOrderWithOrderByProductId(
    Long id
  ) {
    return productOrderMapper.toProductOrderWithOrderDto(
      getModelsByProductId(id)
    );
  }

  /**
   * 3 degree nesting
   */
  @Override
  public List<ProductOrderWithOrderWithUserDTO> getProductOrdersWithOrderWithUserByProductId(
    Long id
  ) {
    return productOrderMapper.toProductOrderWithOrderWithUserDto(
      getModelsByProductId(id)
    );
  }

  /**
   * 4 degree nesting
   */
  @Override
  public List<ProductOrderWithOrderWithUserWithAddressDTO> getProductOrderWithOrderWithUserWithAddressByProductId(
    Long id
  ) {
    return productOrderMapper.toProductOrderWithOrderWithUserWithAddressDto(
      getModelsByProductId(id)
    );
  }
}
