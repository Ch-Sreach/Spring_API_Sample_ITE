package com.example.productsampleapi.service;


import com.example.productsampleapi.dto.ProductRequest;
import com.example.productsampleapi.dto.ProductResponse;
import com.example.productsampleapi.dto.UpdateProductRequest;
import com.example.productsampleapi.entity.Product;
import com.example.productsampleapi.mapper.ProductMapper;
import com.example.productsampleapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    // inject repository
//    private final ProductRepositoryOld productRepositoryOld;
//    private Integer nextId = 1007;

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    //mapToEntity
//    private Product mapToEntity(ProductRequest request) {
//        Product product = new Product();
//        product.setName(request.name());
//        product.setDescription(request.description());
//        product.setPrice(request.price());
//
//        return product;
//    }

    //mapToEntity
    //mapToResponse -> convert Entity to Response
//    private ProductResponse mapToResponse(Product product) {
//        return new ProductResponse(
//                product.getId(),
//                product.getName(),
//                product.getDescription(),
//                product.getPrice()
//        );
//    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.mapToProduct(request);
        product.setUserId(1);
        product.setIsDeleted(false);
        return productMapper.mapToResponse(
                productRepository.save(product)
        );
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToResponse)
                .toList();
    }

    @Override
    public Page<ProductResponse> findAllProducts(Pageable pageable) {
        return productRepository
                .findByIsDeletedFalse(pageable)
                .map(productMapper::mapToResponse);
    }

    @Override
    public ProductResponse findProductById(Integer id) {
//        var product = productRepository.findById(id)
//                .orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));
//        return productMapper.mapToResponse(product);
        Product product = productRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Product with ID = " + id + " not found"
                        ));

        return productMapper.mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {
        var existingProduct = productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));
        if (request.name() != null)
            existingProduct.setName(request.name());
        if (request.description() != null)
            existingProduct.setDescription(request.description());
        if (request.price() != null)
            existingProduct.setPrice(request.price());
        productRepository.save(existingProduct);
        //Product product = mapToEntity(request);
        return productMapper.mapToResponse(existingProduct);
    }

    // TODO: make it like we delete in the category
    @Override
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException(
                    "Product with ID = " + id + " not found"
            );
        }
        productRepository.deleteById(id);
    }

    @Override
    public void softDeleteProduct(Integer id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with ID = " + id + " not found"));
        product.setIsDeleted(true);
        productRepository.save(product);
    }
}
