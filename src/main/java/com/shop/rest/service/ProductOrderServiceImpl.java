package com.shop.rest.service;

import com.shop.rest.config.mapper.ProductOrderMapper;
import com.shop.rest.dto.ProductOrderDTO;
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

  @Override
  public ProductOrderDTO create(
    @NotNull(
      message = "The products for order cannot be null."
    ) @Valid ProductOrderDTO productOrder
  ) {
    return productOrderMapper.toDto(
      productOrderRepository.save(productOrderMapper.toModel(productOrder))
    );
  }

  @Override
  public List<ProductOrderDTO> getAllByProductId(Long id) {
    return productOrderMapper.toDto(
      productOrderRepository.findAllByProduct_Id(id)
    );
  }

  public void deleteAll(List<ProductOrderDTO> productOrders) {
    productOrderRepository.deleteAll(productOrderMapper.toModel(productOrders));
  }

  @Override
  public List<ProductOrderDTO> getAllByOrder_IdIn(List<Long> ids) {
    return productOrderMapper.toDto(
      productOrderRepository.findAllByOrder_IdIn(ids)
    );
  }
}
