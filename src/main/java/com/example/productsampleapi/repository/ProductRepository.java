package com.example.productsampleapi.repository;


import com.example.productsampleapi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByIsDeletedFalse();
    Page<Product> findByIsDeletedFalse(Pageable pageable);
    Optional<Product> findByIdAndIsDeletedFalse(Integer id);
}
