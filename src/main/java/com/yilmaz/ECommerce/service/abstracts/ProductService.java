package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.model.dto.requests.productRequests.CreateProductRequest;
import com.yilmaz.ECommerce.model.dto.requests.productRequests.DeleteProductRequest;
import com.yilmaz.ECommerce.model.dto.requests.productRequests.UpdateProductRequest;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetAllProductsResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductByCategoryIdResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductByIdResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductsByNameResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ResponseEntity<?> addProduct(CreateProductRequest request);
    ResponseEntity<?> updateProduct(Long id, UpdateProductRequest request);
    ResponseEntity<Void> deleteProduct(DeleteProductRequest request);
    ResponseEntity<GetProductByIdResponse> getProductById(Long id);
    ResponseEntity<List<GetProductsByNameResponse>> getProductsByName(String name);
    ResponseEntity<List<GetProductByCategoryIdResponse>> getProductsByCategory(Long categoryId);
    List<GetAllProductsResponse> getAllProducts();

}
