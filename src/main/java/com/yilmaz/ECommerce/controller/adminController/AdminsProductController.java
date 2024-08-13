package com.yilmaz.ECommerce.controller.adminController;

import com.yilmaz.ECommerce.model.dto.requests.productRequests.CreateProductRequest;
import com.yilmaz.ECommerce.model.dto.requests.productRequests.DeleteProductRequest;
import com.yilmaz.ECommerce.model.dto.requests.productRequests.UpdateProductRequest;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductByIdResponse;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetProductsByNameResponse;
import com.yilmaz.ECommerce.service.abstracts.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class AdminsProductController {
    private final ProductService productService;

    public AdminsProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public String getAllProducts(Model model) {

        model.addAttribute("products", productService.getAllProducts());
        return "adminProducts";
    }


    @PostMapping("/create")
    public String createProduct(@ModelAttribute CreateProductRequest request) {
        productService.addProduct(request);
        return "redirect:/api/products/getAll";
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        CreateProductRequest request = new CreateProductRequest();
        model.addAttribute("product", request);
        return "adminCreateProduct";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute UpdateProductRequest request) {
        productService.updateProduct(id, request);
        return "redirect:/api/products/getAll";
    }

    @GetMapping("/update/{id}")
    public String updateProductPage(@PathVariable Long id, Model model) {
        GetProductByIdResponse product = productService.getProductById(id).getBody();
        model.addAttribute("product", product);
        return "adminUpdateProduct";
    }


    @GetMapping("/getByName/{name}")
    public String getProductsByName(@PathVariable String name, Model model) {

        List<GetProductsByNameResponse> products = productService.getProductsByName(name).getBody();
        model.addAttribute("products", products);

        return "adminProducts";
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