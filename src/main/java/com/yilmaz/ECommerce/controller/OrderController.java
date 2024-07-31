package com.yilmaz.ECommerce.controller;

import com.yilmaz.ECommerce.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.dto.requests.orderRequests.UpdateOrderStatusRequest;
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

    @PostMapping("/updateOrderStatus/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest request){
        orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/getOrdersByUserId/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrdersByUserId(id));
    }

}
