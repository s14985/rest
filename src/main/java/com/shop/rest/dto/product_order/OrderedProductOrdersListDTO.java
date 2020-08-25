package com.shop.rest.dto.product_order;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class OrderedProductOrdersListDTO {
	private List<OrderedProductOrderDTO> productOrders;
}
