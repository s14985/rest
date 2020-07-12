package com.shop.rest.controller;

import com.shop.rest.dto.ListedProductDTO;
import com.shop.rest.dto.ProductDTO;
import com.shop.rest.dto.ProductDetailsDTO;
import com.shop.rest.dto.ProductWithProductOrdersDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Product;
import com.shop.rest.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

//  @GetMapping({ "", "/" })
//  public ResponseEntity<Iterable<ProductDTO>> getProducts() {
//    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
//  }

  @GetMapping({ "", "/" })
  public ResponseEntity<Iterable<ListedProductDTO>> getProducts() {
    return new ResponseEntity<>(productService.getListedProducts(), HttpStatus.OK);
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
    product = productService.save(product);
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

  @GetMapping("/{id}")
  public ResponseEntity<ProductDetailsDTO> getProductDetails(
    @PathVariable Long id
  ) {
    return new ResponseEntity<>(
      ProductDetailsDTO
        .builder()
        .product(productService.getProductById(id))
        .suggestedProducts(productService.getProductsFromOrdersByProductId(id))
        .build(),
      HttpStatus.OK
    );
  }

  @GetMapping("/single/{id}")
  public ResponseEntity<ProductDTO> getProduct(
          @PathVariable Long id
  ) {
    return new ResponseEntity<>(
            productService.getProductById(id),
            HttpStatus.OK
    );
  }

  @GetMapping("/full/{id}")
  public ResponseEntity<ProductWithProductOrdersDTO> getFullProduct(
          @PathVariable Long id
  ) {
    return new ResponseEntity<>(
            productService.getFullProduct(id),
            HttpStatus.OK
    );
  }

  @GetMapping("/suggested/{id}")
  public ResponseEntity<Iterable<ProductDTO>> getSuggested(
          @PathVariable Long id
  ) {
    return new ResponseEntity<>(
            productService.getProductsFromOrdersByProductId(id),
            HttpStatus.OK
    );
  }
}
