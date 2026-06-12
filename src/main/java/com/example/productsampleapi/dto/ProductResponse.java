package com.example.productsampleapi.dto;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Float price,
        CategoryResponse categoryResponse
) {
}
