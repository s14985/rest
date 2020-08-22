package com.shop.rest.controller;

import com.shop.rest.dto.product.output.*;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;

	@GetMapping({ "", "/" })
	public ResponseEntity<Iterable<ProductDTO>> getProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@GetMapping("/{id}/suggested")
	public ResponseEntity<Iterable<ProductDTO>> getSuggestedProducts(
		@PathVariable Long id
	) {
		return new ResponseEntity<>(
			productService.getSuggestedProductsFromOrdersByProductId(id),
			HttpStatus.OK
		);
	}

	@PostMapping(
		value = "",
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public ResponseEntity<ProductDTO> createProduct(
		@Valid @RequestBody ProductDTO product
	) {
		product = productService.save(product);
		String uri = ServletUriComponentsBuilder
			.fromCurrentServletMapping()
			.path("/products/{id}")
			.buildAndExpand(product.getId())
			.toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
	}

	@PutMapping(
		value = "",
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public ResponseEntity<ProductDTO> editProduct(
		@Valid @RequestBody ProductDTO product
	) {
		return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteById(id);
			//      return ResponseEntity.noContent().build();
			return new ResponseEntity(true, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("product", "id", id.toString());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
		return new ResponseEntity<>(
			productService.getProductById(id),
			HttpStatus.OK
		);
	}

	@GetMapping("/{id}/product-orders")
	public ResponseEntity<ProductWithProductOrdersDTO> getProductWithProductOrders(
		@PathVariable Long id
	) {
		return new ResponseEntity<>(
			productService.getProductWithProductOrdersById(id),
			HttpStatus.OK
		);
	}

	@GetMapping("/{id}/product-orders/order")
	public ResponseEntity<ProductWithProductOrdersWithOrderDTO> getProductWithProductOrdersWithOrder(
		@PathVariable Long id
	) {
		return new ResponseEntity<>(
			productService.getProductWithProductOrdersWithOrderById(id),
			HttpStatus.OK
		);
	}

	@GetMapping("/{id}/product-orders/order/user")
	public ResponseEntity<ProductWithProductOrdersWithOrderWithUserDTO> getProductWithProductOrdersWithOrderWithUsert(
		@PathVariable Long id
	) {
		return new ResponseEntity<>(
			productService.getProductWithProductOrdersWithOrderWithUserById(id),
			HttpStatus.OK
		);
	}

	@GetMapping("/{id}/product-orders/order/user/address")
	public ResponseEntity<ProductWithProductOrdersWithOrderWithUserWithAddressDTO> getProductWithProductOrdersWithOrderWithUserWithAddress(
		@PathVariable Long id
	) {
		return new ResponseEntity<>(
			productService.getProductWithProductOrdersWithOrderWithUserWithAddressById(
				id
			),
			HttpStatus.OK
		);
	}
}
