package com.example.productsampleapi.dto;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        Boolean isDeleted
) {
}
