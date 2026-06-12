package com.example.productsampleapi.dto;

public record UpdateCategoryRequest(
        String name,
        String description,
        Boolean isDelete
) {
}