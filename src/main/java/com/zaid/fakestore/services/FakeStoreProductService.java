package com.zaid.fakestore.services;

import com.zaid.fakestore.dtos.FakeStoreProductDTO;
import com.zaid.fakestore.dtos.FakeStoreProductDTOPost;
import com.zaid.fakestore.models.Category;
import com.zaid.fakestore.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private final RestTemplate restTemplate = new RestTemplate();

    private Product getProductFromFakeStoreProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageURL(fakeStoreProductDTO.getImage());

        return product;
    }

    private FakeStoreProductDTOPost getFakeStoreProductDTOPostFromProduct(Product product) {
        FakeStoreProductDTOPost fakeStoreProductDTOPost = new FakeStoreProductDTOPost();
        fakeStoreProductDTOPost.setPrice(product.getPrice());
        fakeStoreProductDTOPost.setTitle(product.getTitle());
        fakeStoreProductDTOPost.setCategory(product.getCategory().getName());
        fakeStoreProductDTOPost.setDescription(product.getDescription());
        fakeStoreProductDTOPost.setImage(product.getImageURL());

        return fakeStoreProductDTOPost;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTOs = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
            Product product = getProductFromFakeStoreProductDTO(fakeStoreProductDTO);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );

        return getProductFromFakeStoreProductDTO(fakeStoreProductDTO);
    }

    @Override
    public List<Category> getAllCategories() {
        String[] categoryNames = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );

        List<Category> categories = new ArrayList<>();
        for (String categoryName : categoryNames) {
            Category category = new Category();
            category.setName(categoryName);
            categories.add(category);
        }

        return categories;
    }

    @Override
    public List<Product> getProductsInCategory(String name) {
         FakeStoreProductDTO[] fakeStoreProductDTOs = restTemplate.getForObject(
                 "https://fakestoreapi.com/products/category/" + name,
                 FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
            Product product = getProductFromFakeStoreProductDTO(fakeStoreProductDTO);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.postForObject("https://fakestoreapi.com/products",
                getFakeStoreProductDTOPostFromProduct(product), FakeStoreProductDTO.class);
        return getProductFromFakeStoreProductDTO(fakeStoreProductDTO);
    }

    @Override
    public Product updateProduct(Long id) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );

        return getProductFromFakeStoreProductDTO(fakeStoreProductDTO);
    }
}
