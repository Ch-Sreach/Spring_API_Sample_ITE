package com.example.productsampleapi.mapper;

import com.example.productsampleapi.dto.ProductRequest;
import com.example.productsampleapi.dto.ProductResponse;
import com.example.productsampleapi.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    ProductResponse mapToResponse(Product request);
    Product mapToProduct(ProductRequest request);
}
