package com.shop.rest.dto.product_order.output;

import com.shop.rest.dto.order.output.OrderDTO;
import lombok.Data;

@Data
public class ProductOrderWithOrderDTO {
	private Long id;
	private OrderDTO order;
	private Integer quantity;
}
