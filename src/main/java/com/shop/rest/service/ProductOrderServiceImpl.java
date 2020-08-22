package com.shop.rest.service;

import com.shop.rest.config.mapper.ProductOrderMapper;
import com.shop.rest.dto.product_order.*;
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
	public List<SimpleProductOrderDTO> getFullProductOrdersByProductId(Long id) {
		return productOrderMapper.toSimpleProductOrderDto(getModelsByProductId(id));
	}

	public void deleteAll(List<SimpleProductOrderDTO> productOrders) {
		productOrderRepository.deleteAll(
			productOrderMapper.simpleProductOrderToModel(productOrders)
		);
	}

	@Override
	public List<SimpleProductOrderDTO> getSimpleProductOrderByOrdersIdIn(
		List<Long> ids
	) {
		return productOrderMapper.toSimpleProductOrderDto(
			productOrderRepository.findAllByOrdersIdIn(ids)
		);
	}

	@Override
	public List<SimpleProductOrderDTO> getSimpleProductOrdersByProductId(
		Long id
	) {
		return productOrderMapper.toSimpleProductOrderDto(getModelsByProductId(id));
	}

	@Override
	public List<ProductOrderWithOrderDTO> getProductOrderWithOrderByProductId(
		Long id
	) {
		return productOrderMapper.toProductOrderWithOrderDto(
			getModelsByProductId(id)
		);
	}

	@Override
	public List<ProductOrderWithOrderWithUserDTO> getProductOrdersWithOrderWithUserByProductId(
		Long id
	) {
		return productOrderMapper.toProductOrderWithOrderWithUserDto(
			getModelsByProductId(id)
		);
	}

	@Override
	public List<ProductOrderWithOrderWithUserWithAddressDTO> getProductOrderWithOrderWithUserWithAddressByProductId(
		Long id
	) {
		return productOrderMapper.toProductOrderWithOrderWithUserWithAddressDto(
			getModelsByProductId(id)
		);
	}
}
