package com.shop.rest.config.mapper;

import com.shop.rest.dto.product_order.*;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserDTO;
import com.shop.rest.dto.product_order.output.ProductOrderWithOrderWithUserWithAddressDTO;
import com.shop.rest.model.ProductOrder;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductOrderMapper {
	List<ProductOrder> simpleProductOrderToModel(
		List<SimpleProductOrderDTO> dtos
	);

	List<SimpleProductOrderDTO> toSimpleProductOrderDto(List<ProductOrder> model);

	ProductOrder createdProductOrderToModel(CreatedProductOrderDTO dto);

	CreatedProductOrderDTO toCreatedProductOrderDto(ProductOrder model);

	List<ProductOrder> createdProductOrderToModel(
		List<CreatedProductOrderDTO> dtos
	);

	List<ProductOrderWithOrderDTO> toProductOrderWithOrderDto(
		List<ProductOrder> models
	);

	List<ProductOrderWithOrderWithUserDTO> toProductOrderWithOrderWithUserDto(
		List<ProductOrder> models
	);

	List<ProductOrderWithOrderWithUserWithAddressDTO> toProductOrderWithOrderWithUserWithAddressDto(
		List<ProductOrder> models
	);
}
