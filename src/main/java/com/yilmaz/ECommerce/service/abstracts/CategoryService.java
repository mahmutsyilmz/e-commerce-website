package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.dto.requests.categoryRequests.CreateCategoryRequest;
import com.yilmaz.ECommerce.dto.requests.categoryRequests.DeleteCategoryRequest;
import com.yilmaz.ECommerce.dto.requests.categoryRequests.UpdateCategoryRequest;
import com.yilmaz.ECommerce.dto.responses.categoryResponses.GetAllCategoriesResponse;
import com.yilmaz.ECommerce.dto.responses.categoryResponses.GetAllCategoriesWithProducts;
import com.yilmaz.ECommerce.dto.responses.categoryResponses.GetCategoriesByNameResponse;
import com.yilmaz.ECommerce.dto.responses.categoryResponses.GetCategoryByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    ResponseEntity<?> addCategory(CreateCategoryRequest request);
    ResponseEntity<?> updateCategory(Long id, UpdateCategoryRequest request);
    ResponseEntity<Void> deleteCategory(DeleteCategoryRequest request);
    ResponseEntity<GetCategoryByIdResponse> getCategoryById(Long id);
    ResponseEntity<List<GetCategoriesByNameResponse>> getCategoriesByName(String name);
    ResponseEntity<List<GetAllCategoriesResponse>> getAllCategories();
    ResponseEntity<List<GetAllCategoriesWithProducts>> getAllCategoriesWithProducts();
}
