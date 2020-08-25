package com.shop.rest.dto.product.output;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class ProductDTO {
	private Long id;

	private String name;

	private BigDecimal price;

	private String picture;

	private String details;

	private String manufacturer;

	private String itemCode;

	private String color;

	private String material;

	public ProductDTO(Long id) {
		this.id = id;
	}
}
