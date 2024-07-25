package com.yilmaz.ECommerce.controller;

import com.yilmaz.ECommerce.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.service.abstracts.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/createOrderItem")
    public ResponseEntity<?> createOrderItem(@RequestBody CreateOrderItemRequest request){
        orderItemService.createOrderItem(request);
        return ResponseEntity.ok(request);
    }
}
