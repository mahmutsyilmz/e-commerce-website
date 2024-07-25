package com.yilmaz.ECommerce.controller;

import com.yilmaz.ECommerce.dto.requests.categoryRequests.CreateCategoryRequest;
import com.yilmaz.ECommerce.dto.requests.categoryRequests.DeleteCategoryRequest;
import com.yilmaz.ECommerce.dto.requests.categoryRequests.UpdateCategoryRequest;
import com.yilmaz.ECommerce.dto.responses.categoryResponses.GetCategoryByIdResponse;
import com.yilmaz.ECommerce.service.abstracts.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CreateCategoryRequest request) {
        categoryService.addCategory(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody UpdateCategoryRequest request) {
        categoryService.updateCategory(id, request);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody DeleteCategoryRequest request) {
         categoryService.deleteCategory(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GetCategoryByIdResponse> getCategoryById(@PathVariable Long id) {
        categoryService.getCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getCategoriesByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getCategoriesByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/getAllWithProducts")
    public ResponseEntity<?> getAllCategoriesWithProducts() {
        return ResponseEntity.ok(categoryService.getAllCategoriesWithProducts());
    }


}
