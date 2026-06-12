package com.example.productsampleapi.restcontroller;

import com.example.productsampleapi.dto.ProductRequest;
import com.example.productsampleapi.dto.ProductResponse;
import com.example.productsampleapi.dto.UpdateProductRequest;
import com.example.productsampleapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService productService;

//    public List<ProductResponse> getProducts() {
//        return productService.findAllProducts();
//    }

    @GetMapping
    public Page<ProductResponse> getProducts(Pageable pageable) {
        return productService.findAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Integer id,
            @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}/soft-delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteProduct(@PathVariable Integer id) {
        productService.softDeleteProduct(id);
    }
}
