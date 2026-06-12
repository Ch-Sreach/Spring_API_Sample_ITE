package com.example.productsampleapi.service;

import com.example.productsampleapi.advisor.ResourceAlreadyExistException;
import com.example.productsampleapi.dto.CategoryRequest;
import com.example.productsampleapi.dto.CategoryResponse;
import com.example.productsampleapi.dto.UpdateCategoryRequest;
import com.example.productsampleapi.entity.Category;
import com.example.productsampleapi.mapper.CategoryMapper;
import com.example.productsampleapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
//@Primary
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        if (categoryRepository.existsByName(request.name())) {
            throw new ResourceAlreadyExistException(
                    "Category with name= " + request.name() + " already exists"
            );
        }
        category.setIsDeleted(false);
        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
//        return List.of();
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public Page<CategoryResponse> findAllCategory(Pageable pageable) {
        return categoryRepository
                .findByIsDeletedFalse(pageable)
                .map(categoryMapper::toResponse);
//        return null;
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        Category existingCategory = categoryRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Category with ID = " + id + " not found"));
        if (request.name() != null
                && !existingCategory.getName().equalsIgnoreCase(request.name())
                && categoryRepository.existsByName(request.name())) {
            throw new ResourceAlreadyExistException("Category with name = " + request.name() + " already exists");
        }
        if (request.name() != null) {
            existingCategory.setName(request.name());
        }
        if (request.description() != null) {
            existingCategory.setDescription(request.description());
        }
        if (request.isDelete() != null) {
            existingCategory.setIsDeleted(request.isDelete());
        }
        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponse(updatedCategory);
    }


    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Category with id = " + id + " not found"));
        return categoryMapper.toResponse(category);
//        return null;
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Category with id = " + id + " does not exist")
                );
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }


    @Override
    public void softDeleteCategory(Integer id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Category with id = " + id + " not found"));
        category.setIsDeleted(true);
        categoryRepository.save(category);

    }

    @Override
    public List<CategoryResponse> findByName(String name) {
        return categoryRepository
                .findByNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
//        return List.of();
    }
}
