package com.zaid.fakestore.services;

import com.zaid.fakestore.models.Category;
import com.zaid.fakestore.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long id);
    List<Category> getAllCategories();
    List<Product> getProductsInCategory(String name);
    Product addProduct(Product product);
    Product updateProduct(Long id);
    Product deleteProduct(Long id);
}
