package com.shop.rest.controller;

import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Product;
import com.shop.rest.service.ProductService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  @GetMapping({ "", "/" })
  public @NotNull Iterable<Product> getProducts() {
    return productService.getAllProducts();
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public @NotNull Product getProduct(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(
    @Valid @RequestBody Product product
  ) {
    this.productService.save(product);
    String uri = ServletUriComponentsBuilder
      .fromCurrentServletMapping()
      .path("/products/{id}")
      .buildAndExpand(product.getId())
      .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
    try {
      this.productService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("product", "id", id.toString());
    }
  }
}
