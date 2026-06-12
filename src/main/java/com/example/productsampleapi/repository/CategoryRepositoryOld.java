//package com.example.productsampleapi.repository;
//
//
//import com.example.productsampleapi.entity.Category;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Repository
//public class CategoryRepositoryOld {
//    private List<Category> categoryList = new ArrayList<>() {{
//        add(new Category(1, "Beverages", "Soft drinks and beverages", true, null));
//        add(new Category(2, "Snacks", "Chips and snack products",true, null));
//        add(new Category(3, "Dairy", "Milk and dairy products", true, null));
//        add(new Category(4, "Frozen Food", "Frozen food products", true, null));
//    }};
//
//    public List<Category> getAllCategory() {
//        return categoryList;
//    }
//
//    public Category createCategory(Category category) {
//        categoryList.add(category);
//        return category;
//    }
//
//    public Category findCategoryById(Integer id) {
//        return categoryList.stream()
//                .filter(category -> category.getId() == id)
//                .findFirst()
//                .orElseThrow(() ->
//                        new NoSuchElementException("Category with this ID: " + id + " not found"));
//    }
//
//    public boolean deleteCategoryById(Integer id) {
//        return categoryList
//                .removeIf(category -> category.getId() == id);
//    }
//
//    public Category updateCategoryById(Category updateCategory) {
//        for (int i = 0; i < categoryList.size(); i++) {
//            var category = categoryList.get(i);
//
//            if (category.getId() == updateCategory.getId()) {
//                categoryList.set(i, updateCategory);
//                return updateCategory;
//            }
//        }
//        return null;
//    }
//}
