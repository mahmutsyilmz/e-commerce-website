package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.model.dto.requests.categoryRequests.CreateCategoryRequest;
import com.yilmaz.ECommerce.model.dto.requests.categoryRequests.UpdateCategoryRequest;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetAllCategoriesResponse;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetAllCategoriesWithProducts;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetCategoriesByNameResponse;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetCategoryByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    ResponseEntity<?> addCategory(CreateCategoryRequest request);
    ResponseEntity<?> updateCategory(Long id, UpdateCategoryRequest request);
    ResponseEntity<Void> deleteCategory(Long id);
    ResponseEntity<GetCategoryByIdResponse> getCategoryById(Long id);
    ResponseEntity<List<GetCategoriesByNameResponse>> getCategoriesByName(String name);
    List<GetAllCategoriesResponse> getAllCategories();
    ResponseEntity<List<GetAllCategoriesWithProducts>> getAllCategoriesWithProducts();
}
