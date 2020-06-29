package com.shop.rest.controller;

import com.shop.rest.dto.ProductDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
    return new ResponseEntity<>(
      productService.getProductById(id),
      HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(
    @Valid @RequestBody ProductDTO product
  ) {
    productService.save(product);
    String uri = ServletUriComponentsBuilder
      .fromCurrentServletMapping()
      .path("/products/{id}")
      .buildAndExpand(product.getId())
      .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<ProductDTO> editProduct(
    @RequestBody ProductDTO product
  ) {
    productService.save(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
    try {
      productService.deleteById(id);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("product", "id", id.toString());
    }
  }

  @GetMapping("/suggested-items/{id}")
  public ResponseEntity<Iterable<ProductDTO>> test(@PathVariable Long id) {
    return new ResponseEntity<>(
      productService.findProductsFromOrdersByProductId(id),
      HttpStatus.OK
    );
  }
}
