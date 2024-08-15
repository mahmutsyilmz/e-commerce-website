package com.yilmaz.ECommerce.controller.adminController;

import com.yilmaz.ECommerce.model.dto.requests.categoryRequests.CreateCategoryRequest;
import com.yilmaz.ECommerce.model.dto.requests.categoryRequests.UpdateCategoryRequest;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetAllCategoriesResponse;
import com.yilmaz.ECommerce.model.dto.responses.categoryResponses.GetCategoryByIdResponse;
import com.yilmaz.ECommerce.service.abstracts.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class AdminsCategoryController {
    private final CategoryService categoryService;

    public AdminsCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("getAll")
    public String getAllCategories(Model model) {

        List<GetAllCategoriesResponse> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "adminCategories";
    }
    @GetMapping("/create")
    public String createCategoryPage(Model model) {
        CreateCategoryRequest request = new CreateCategoryRequest();
        model.addAttribute("category", request);
        return "adminCreateCategory";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CreateCategoryRequest request) {
        categoryService.addCategory(request);
        return "redirect:/api/categories/getAll";
    }
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute UpdateCategoryRequest request) {
        categoryService.updateCategory(id, request);
        return "redirect:/api/categories/getAll";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/api/categories/getAllCategories";
    }


//    @GetMapping("/get/{id}")
//    public ResponseEntity<GetCategoryByIdResponse> getCategoryById(@PathVariable Long id) {
//        categoryService.getCategoryById(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/getByName/{name}")
//    public ResponseEntity<?> getCategoriesByName(@PathVariable String name) {
//        return ResponseEntity.ok(categoryService.getCategoriesByName(name));
//    }
//
//
//    @GetMapping("/getAllWithProducts")
//    public ResponseEntity<?> getAllCategoriesWithProducts() {
//        return ResponseEntity.ok(categoryService.getAllCategoriesWithProducts());
//    }


}
