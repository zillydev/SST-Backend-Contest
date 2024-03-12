package com.zaid.fakestore.controllers;

import com.zaid.fakestore.models.Category;
import com.zaid.fakestore.models.Product;
import com.zaid.fakestore.services.FakeStoreProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private final FakeStoreProductService productService;

    public CategoryController(FakeStoreProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/products/category/{name}")
    public List<Product> getProductsInCategory(@PathVariable("name") String categoryId) {
        return productService.getProductsInCategory(categoryId);
    }
}
