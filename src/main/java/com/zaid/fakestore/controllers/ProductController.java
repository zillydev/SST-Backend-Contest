package com.zaid.fakestore.controllers;

import com.zaid.fakestore.models.Product;
import com.zaid.fakestore.services.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final FakeStoreProductService productService;

    public ProductController(FakeStoreProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cart")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/cart/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping("/cart")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/cart/{id}")
    public Product updateProduct(@PathVariable("id") Long id) {
        return productService.updateProduct(id);
    }

    @DeleteMapping("/cart/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}
