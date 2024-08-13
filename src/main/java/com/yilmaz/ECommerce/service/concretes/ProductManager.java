package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.model.dto.requests.productRequests.CreateProductRequest;
import com.yilmaz.ECommerce.model.dto.requests.productRequests.DeleteProductRequest;
import com.yilmaz.ECommerce.model.dto.requests.productRequests.UpdateProductRequest;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetAllProductsResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductByCategoryIdResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductByIdResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductsByNameResponse;
import com.yilmaz.ECommerce.core.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Category;
import com.yilmaz.ECommerce.model.concretes.Product;
import com.yilmaz.ECommerce.repository.abstracts.CategoryRepository;
import com.yilmaz.ECommerce.repository.abstracts.ProductRepository;
import com.yilmaz.ECommerce.service.abstracts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ResponseEntity<?> addProduct(CreateProductRequest request) {
        //map without using modelMapperService
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        product.setCategory(category);
        productRepository.save(product);
        return ResponseEntity.ok(request);
    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found!"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        product.setCategory(category);
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(DeleteProductRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("Product not found!"));
        productRepository.delete(product);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<GetProductByIdResponse> getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found!"));
        GetProductByIdResponse response = modelMapperService.forResponse().map(product, GetProductByIdResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<GetProductsByNameResponse>> getProductsByName(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        List<GetProductsByNameResponse> responses = products.stream()
                .map(product -> modelMapperService.forResponse().map(product, GetProductsByNameResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<GetProductByCategoryIdResponse>> getProductsByCategory(Long categoryId){
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<GetProductByCategoryIdResponse> responses = products.stream()
                .map(product -> modelMapperService.forResponse().map(product, GetProductByCategoryIdResponse.class))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public List<GetAllProductsResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductsResponse> responses = products.stream()
                .map(product -> modelMapperService.forResponse().map(product, GetAllProductsResponse.class))
                .toList();
        return responses;
    }
}