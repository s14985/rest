package com.shop.rest.service;

import com.shop.rest.config.mapper.OrderMapper;
import com.shop.rest.config.mapper.ProductOrderMapper;
import com.shop.rest.dto.order.output.OrderWithUserDTO;
import com.shop.rest.dto.product_order.CreatedProductOrderDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Order;
import com.shop.rest.repository.OrderRepository;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final ProductOrderMapper productOrderMapper;

	private Order getModel(
		@Min(value = 1L, message = "Invalid order ID.") Long id
	) {
		return orderRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("order", "id", id.toString())
			);
	}

	@Override
	public OrderWithUserDTO create(
		@NotNull(
			message = "The order cannot be null."
		) @Valid OrderWithUserDTO order
	) {
		order.setDateCreated(OffsetDateTime.now());
		return orderMapper.toOrderWithUserDto(
			orderRepository.save(orderMapper.toModel(order))
		);
	}

	@Override
	public OrderWithUserDTO update(
		@NotNull(
			message = "The order cannot be null."
		) @Valid OrderWithUserDTO order
	) {
		return orderMapper.toOrderWithUserDto(
			orderRepository.save(orderMapper.toModel(order))
		);
	}

	@Override
	public OrderWithUserDTO setOrder(
		OrderWithUserDTO orderWithUserDTO,
		List<CreatedProductOrderDTO> productOrders
	) {
		Order order = orderMapper.toModel(orderWithUserDTO);
		order.setProductOrders(
			productOrderMapper.createdProductOrderToModel(productOrders)
		);
		return orderMapper.toOrderWithUserDto(order);
	}

	@Override
	public OrderWithUserDTO getOrderWithUserById(Long id) {
		return orderMapper.toOrderWithUserDto(getModel(id));
	}
}
