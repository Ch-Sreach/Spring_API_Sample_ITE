//package com.example.productsampleapi.repository;
//
//
//
//import com.example.productsampleapi.entity.Product;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Repository
//public class ProductRepositoryOld {
//    // because we don't work with database yet
//    // productList = represent the data storage
//    private List<Product> productList = new ArrayList<>(){{
//        add(new Product(1001, "Coca", "Nice to See Yaa", 23.20f, null, null));
//        add(new Product(1002, "Ize", "Best ice cola", 23.12f,null,null));
//        add(new Product(1003, "Fanto", "Good tasty", 21.12f,null,null));
//        add(new Product(1004, "Pepci", "Best for vocation", 24.12f,null,null));
//    }};
//    public List<Product> getAllProduct() {
//        return productList;
//    }
//    public Product createProduct(Product product) {
//        productList.add(product);
//        return product;
//    }
//    public Product findProductById(Integer id) {
//        return productList.stream()
//                .filter(product -> product.getId() == id)
//                .findFirst()
//                .orElseThrow(
//                        () -> new NoSuchElementException("Product with this ID: " + id + " not found"));
//    }
//    public boolean deleteProductById(Integer id){
//        return productList
//                .removeIf(product -> product.getId() == id);
//    }
//    public Product updateProductById(Product updateProduct) {
//        for (int i = 0; i < productList.size(); i++) {
//            var product = productList.get(i);
//            if (product.getId() == updateProduct.getId()) {
//                productList.set(i, updateProduct);
//                return updateProduct;
//            }
//        }
//        return null;
//    }
//}