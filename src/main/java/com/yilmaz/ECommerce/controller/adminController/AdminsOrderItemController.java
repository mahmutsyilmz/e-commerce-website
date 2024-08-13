package com.yilmaz.ECommerce.controller.adminController;

import com.yilmaz.ECommerce.model.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.model.dto.responses.productResponses.GetAllProductsResponse;
import com.yilmaz.ECommerce.service.abstracts.OrderItemService;
import com.yilmaz.ECommerce.service.abstracts.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/orderItems")
public class AdminsOrderItemController {
    private final OrderItemService orderItemService;
    private final ProductService productService;

    public AdminsOrderItemController(OrderItemService orderItemService, ProductService productService) {
        this.orderItemService = orderItemService;
        this.productService = productService;
    }

//    @PostMapping("/createOrderItem")
//    public ResponseEntity<?> createOrderItem(@RequestBody CreateOrderItemRequest request){
//        orderItemService.createOrderItem(request);
//        return ResponseEntity.ok(request);
//    }

    @PostMapping("/createOrderItem")
    public String createOrderItem(@ModelAttribute CreateOrderItemRequest request){
        orderItemService.createOrderItem(request);
        return "redirect:/api/orders/getAll/" + request.getUserId();
    }

    @GetMapping("/createOrderItem/{id}")
    public String createOrderItemPage(@PathVariable Long id, Model model){
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        request.setOrderId(id); // Set the order ID
        model.addAttribute("orderItem", request);

        List<GetAllProductsResponse> products = productService.getAllProducts(); // Tüm ürünleri al
        model.addAttribute("products", products); // Ürünleri model nesnesine ekle

        return "createOrderItem";
    }

    @PostMapping("/deleteOrderItem/{id}")
    public String deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return "redirect:/api/orders/getAll";
    }


}
