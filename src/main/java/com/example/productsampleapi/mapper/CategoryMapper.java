package com.example.productsampleapi.mapper;

import com.example.productsampleapi.dto.CategoryRequest;
import com.example.productsampleapi.dto.CategoryResponse;
import com.example.productsampleapi.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // Convert Entity → Response DTO
    CategoryResponse toResponse(Category category);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Category toEntity(CategoryRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    void updateCategoryFromRequest(CategoryRequest request, @org.mapstruct.MappingTarget Category category);
}