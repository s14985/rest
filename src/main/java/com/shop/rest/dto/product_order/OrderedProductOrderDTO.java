package com.shop.rest.dto.product_order;

import com.shop.rest.dto.product.output.ProductDTO;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductOrderDTO {
	private ProductDTO product;
	private Integer quantity;
}
