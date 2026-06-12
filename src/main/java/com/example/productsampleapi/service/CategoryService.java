package com.example.productsampleapi.service;

import com.example.productsampleapi.dto.CategoryRequest;
import com.example.productsampleapi.dto.CategoryResponse;
import com.example.productsampleapi.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> findAllCategory();
    // Get all categories with pagination
    Page<CategoryResponse> findAllCategory(Pageable pageable);
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest categoryRequest);
    CategoryResponse findCategoryById(Integer id);
    void deleteCategory(Integer id);
    // Soft delete (set isDeleted = true)
    void softDeleteCategory(Integer id);
    List<CategoryResponse> findByName(String name);
}
