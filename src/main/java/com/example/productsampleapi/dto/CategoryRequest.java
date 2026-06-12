package com.example.productsampleapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @Size(min = 1, max = 100)
        @NotBlank(message = "name is required")
        String name,
        @Size(min = 1, max = 100)
        @NotBlank(message = "description is required")
        String description
//        @NotNull(message = "Not Null for Action of Category")
//        Boolean isDelete
) {
}
