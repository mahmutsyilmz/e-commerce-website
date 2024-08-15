package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.model.dto.requests.categoryRequests.CreateCategoryRequest;
import com.yilmaz.ECommerce.model.dto.requests.categoryRequests.UpdateCategoryRequest;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetAllCategoriesResponse;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetAllCategoriesWithProducts;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetCategoriesByNameResponse;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetCategoryByIdResponse;
import com.yilmaz.ECommerce.core.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Category;
import com.yilmaz.ECommerce.model.concretes.Product;
import com.yilmaz.ECommerce.repository.abstracts.CategoryRepository;
import com.yilmaz.ECommerce.service.abstracts.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final ModelMapperService modelMapperService;

    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public ResponseEntity<?> addCategory(CreateCategoryRequest request) {
        Category category = this.modelMapperService.forRequest().map(request, Category.class);
        this.categoryRepository.save(category);
        return ResponseEntity.ok(request);
    }

    @Override
    public ResponseEntity<?> updateCategory(Long id, UpdateCategoryRequest request) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        this.modelMapperService.forRequest().map(request, category);
        this.categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        this.categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<GetCategoryByIdResponse> getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        GetCategoryByIdResponse response = this.modelMapperService.forResponse().map(category, GetCategoryByIdResponse.class);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<GetCategoriesByNameResponse>> getCategoriesByName(String name) {
        List<Category> categories = this.categoryRepository.findByNameContaining(name);
        List<GetCategoriesByNameResponse> responses = categories.stream()
                .map(category -> this.modelMapperService.forResponse().map(category, GetCategoriesByNameResponse.class))
                .toList();

        return ResponseEntity.ok(responses);
    }

    @Override
    public List<GetAllCategoriesResponse> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<GetAllCategoriesResponse> responses = categories.stream()
                .map(category -> this.modelMapperService.forResponse().map(category, GetAllCategoriesResponse.class))
                .toList();


        return responses;

    }

    @Override
    public ResponseEntity<List<GetAllCategoriesWithProducts>> getAllCategoriesWithProducts() {
        List<Category> categories = this.categoryRepository.findAll();
        List<GetAllCategoriesWithProducts> responses = categories.stream()
                .map(category -> {
                    // Category'den GetAllCategoriesWithProducts DTO'ya dönüştürme
                    GetAllCategoriesWithProducts dto = this.modelMapperService.forResponse().map(category, GetAllCategoriesWithProducts.class);
                    // Ürün sayısını manuel olarak ayarlayın
                    dto.setProductCount(category.getProducts().size());

                    // Product adlarını listeye dönüştürme
                    List<String> productNames = category.getProducts().stream()
                            .map(Product::getName)
                            .collect(Collectors.toList());

                    dto.setProductNames(productNames);
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
