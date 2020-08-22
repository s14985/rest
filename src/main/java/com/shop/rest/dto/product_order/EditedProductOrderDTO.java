package com.shop.rest.dto.product_order;

import java.util.List;
import lombok.Data;

@Data
public class EditedProductOrderDTO {
	private Long id;
	private List<OrderedProductOrderDTO> productOrders;
}
