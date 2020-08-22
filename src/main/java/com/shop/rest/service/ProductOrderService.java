package com.shop.rest.service;

import com.shop.rest.dto.product_order.*;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserWithAddressDTO;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public interface ProductOrderService {
	CreatedProductOrderDTO create(
		@NotNull(
			message = "The products for order cannot be null."
		) @Valid CreatedProductOrderDTO productOrder
	);

	List<SimpleProductOrderDTO> getFullProductOrdersByProductId(Long id);

	List<SimpleProductOrderDTO> getSimpleProductOrdersByProductId(Long id);

	void deleteAll(List<SimpleProductOrderDTO> productOrders);

	List<SimpleProductOrderDTO> getSimpleProductOrderByOrdersIdIn(List<Long> ids);

	List<ProductOrderWithOrderDTO> getProductOrderWithOrderByProductId(Long id);

	List<ProductOrderWithOrderWithUserDTO> getProductOrdersWithOrderWithUserByProductId(
		Long id
	);

	List<ProductOrderWithOrderWithUserWithAddressDTO> getProductOrderWithOrderWithUserWithAddressByProductId(
		Long id
	);
}
