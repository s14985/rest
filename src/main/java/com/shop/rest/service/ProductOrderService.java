package com.shop.rest.service;

import com.shop.rest.dto.ProductOrderDTO;
import com.shop.rest.model.ProductOrder;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductOrderService {
  ProductOrderDTO create(
    @NotNull(
      message = "The products for order cannot be null."
    ) @Valid ProductOrderDTO productOrder
  );

  List<ProductOrderDTO> getAllByProductId(Long id);

  void deleteAll(List<ProductOrderDTO> productOrders);

  List<ProductOrderDTO> getAllByOrder_IdIn(List<Long> ids);
}
