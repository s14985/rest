package com.shop.rest.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.shop.rest.dto.product.output.ProductDTO;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProductIntegrationTest {
	private static final String PRODUCTS_URL =
		"http://localhost:8082/api/products";

	@Test
	void getProduct_shouldReturnProduct() {
		given()
			.get(PRODUCTS_URL + "/1")
			.then()
			.statusCode(200)
			.and()
			.body("id", is(1))
			.and()
			.body("name", is("product 1"));
	}

	@Test
	void getProductWithProductOrders_shouldReturnProductWithProductOrders() {
		given()
			.get(PRODUCTS_URL + "/1/product-orders")
			.then()
			.statusCode(200)
			.and()
			.body("id", is(1))
			.and()
			.body("name", is("product 1"))
			.and()
			.body("productOrders.size()", is(4))
			.and()
			.body("productOrders[0].id", is(14947));
	}

	@Test
	void getProductWithProductOrdersWithOrder_shouldReturnProductWithProductOrdersWithOrder() {
		given()
			.get(PRODUCTS_URL + "/1/product-orders/order")
			.then()
			.statusCode(200)
			.and()
			.body("id", is(1))
			.and()
			.body("name", is("product 1"))
			.and()
			.body("productOrders.size()", is(4))
			.and()
			.body("productOrders[0].id", is(14947))
			.and()
			.body("productOrders[0].order.status", is("CREATED"));
	}

	@Test
	void getProductWithProductOrdersWithOrderWithUser_shouldReturnProductWithProductOrdersWithOrderWithUser() {
		given()
			.get(PRODUCTS_URL + "/1/product-orders/order/user")
			.then()
			.statusCode(200)
			.and()
			.body("id", is(1))
			.and()
			.body("name", is("product 1"))
			.and()
			.body("productOrders.size()", is(4))
			.and()
			.body("productOrders[0].id", is(14947))
			.and()
			.body("productOrders[0].order.status", is("CREATED"))
			.and()
			.body("productOrders[0].order.user.username", is("user_7888"));
	}

	@Test
	void getProductWithProductOrdersWithOrderWithUserWithAddress_shouldReturnProductWithProductOrdersWithOrderWithUserWithAddress() {
		given()
			.get(PRODUCTS_URL + "/1/product-orders/order/user/address")
			.then()
			.statusCode(200)
			.and()
			.body("id", is(1))
			.and()
			.body("name", is("product 1"))
			.and()
			.body("productOrders.size()", is(4))
			.and()
			.body("productOrders[0].id", is(14947))
			.and()
			.body("productOrders[0].order.status", is("CREATED"))
			.and()
			.body("productOrders[0].order.user.username", is("user_7888"))
			.and()
			.body("productOrders[0].order.user.address.street", is("street 7888"));
	}

	@Test
	void getSuggestedProducts_shouldReturnProducts() {
		given()
			.get(PRODUCTS_URL + "/1/suggested")
			.then()
			.statusCode(200)
			.and()
			.body("$.size()", is(21));
	}

	@Test
	void createProduct_shouldReturnProduct() {
		given()
			.contentType(ContentType.JSON)
			.body(
				ProductDTO.builder().name("new product").price(BigDecimal.TEN).build()
			)
			.post(PRODUCTS_URL)
			.then()
			.statusCode(201)
			.and()
			.body("name", is("new product"));
	}

	@Test
	void editProduct_shouldReturnProduct() {
		given()
			.contentType(ContentType.JSON)
			.body(
				ProductDTO
					.builder()
					.id(100001L)
					.name("edited product")
					.price(BigDecimal.TEN)
					.build()
			)
			.put(PRODUCTS_URL)
			.then()
			.statusCode(200)
			.and()
			.body("name", is("edited product"));
	}

	@Test
	void deleteProduct() {
		given()
			.delete(PRODUCTS_URL + "/100001")
			.then()
			.statusCode(200)
			.and()
			.body(is("true"));
	}
}
