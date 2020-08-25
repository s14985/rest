package com.shop.rest.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.shop.rest.dto.product.output.ProductDTO;
import com.shop.rest.dto.product_order.OrderedProductOrderDTO;
import com.shop.rest.dto.product_order.OrderedProductOrdersListDTO;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderIntegrationTest {
	private static final String ORDERS_URL = "http://localhost:8082/api/orders";

	@Test
	void createOrder() {
		given()
			.contentType(ContentType.JSON)
			.body(
				OrderedProductOrdersListDTO
					.builder()
					.productOrders(
						List.of(
							new OrderedProductOrderDTO(
								ProductDTO.builder().id(1L).build(),
								5
							),
							new OrderedProductOrderDTO(
							        ProductDTO.builder().id(2L).build(),
                                    6
                            )
						)
					)
					.build()
			)
			.post(ORDERS_URL)
			.then()
			.statusCode(201)
			.and()
			.body("id", is(50001))
			.and()
			.body("status", is("CREATED"));
	}
}
