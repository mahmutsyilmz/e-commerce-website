package com.yilmaz.ECommerce.controller;

import com.yilmaz.ECommerce.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request){
        orderService.createOrder(request);
        return ResponseEntity.ok(request);
    }

//    @PostMapping("/updateOrder/{id}")
//    public ResponseEntity<?> updateOrder(@PathVariable Long id,@RequestBody UpdateOrderRequest request){
//        orderService.updateOrder(request);
//        return ResponseEntity.ok(request);
//    }
}
