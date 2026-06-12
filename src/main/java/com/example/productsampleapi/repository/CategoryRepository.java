package com.example.productsampleapi.repository;

import com.example.productsampleapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Boolean existsByName(String name);
    Page<Category> findByIsDeletedFalse(Pageable pageable);
    Optional<Category> findByIdAndIsDeletedFalse(Integer id);
    List<Category> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);
}
