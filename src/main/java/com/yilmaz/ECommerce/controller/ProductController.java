package com.yilmaz.ECommerce.controller;

import com.yilmaz.ECommerce.dto.requests.productRequests.CreateProductRequest;
import com.yilmaz.ECommerce.dto.requests.productRequests.DeleteProductRequest;
import com.yilmaz.ECommerce.dto.requests.productRequests.UpdateProductRequest;
import com.yilmaz.ECommerce.dto.responses.productResponses.GetProductsByNameResponse;
import com.yilmaz.ECommerce.service.abstracts.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody CreateProductRequest request) {
        productService.addProduct(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        productService.updateProduct(id, request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getProductsByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductsByName(name));

    }

    @GetMapping("/getByCategory/{categoryId}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody DeleteProductRequest request) {
        return productService.deleteProduct(request);
    }
}