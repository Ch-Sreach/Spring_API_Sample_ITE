package com.example.productsampleapi.service;

import com.example.productsampleapi.dto.ProductRequest;
import com.example.productsampleapi.dto.ProductResponse;
import com.example.productsampleapi.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse> findAllProducts();
    Page<ProductResponse> findAllProducts(Pageable pageable);
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest request);
    void deleteProduct(Integer id);
    void softDeleteProduct(Integer id);
}
