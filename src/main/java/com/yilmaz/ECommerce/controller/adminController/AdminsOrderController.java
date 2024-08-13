package com.yilmaz.ECommerce.controller.adminController;

import com.yilmaz.ECommerce.model.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.model.dto.requests.orderRequests.UpdateOrderStatusRequest;
import com.yilmaz.ECommerce.model.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class AdminsOrderController {
    private final OrderService orderService;

    public AdminsOrderController(OrderService orderService) {
        this.orderService = orderService;
    }




//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllOrders() {
//        return ResponseEntity.ok(orderService.getAllOrders());
//    }

    @GetMapping("/getAll")
    public String getAllOrders(Model model) {
        List<GetAllOrdersResponse> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders); // Burada "orders" anahtar kelimesini kullanıyoruz
        return "adminOrders"; // Bu "adminOrders.html" dosyasına yönlendirir
    }

//    @PostMapping("/createOrder")
//    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request){
//        orderService.createOrder(request);
//        return ResponseEntity.ok(request);
//    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute CreateOrderRequest request){
        orderService.createOrder(request);
        return "redirect:/api/orders/getAll";
    }

    @GetMapping("/createOrder")
    public String createOrderPage(Model model){
        CreateOrderRequest request = new CreateOrderRequest();
        model.addAttribute("order", request);
        return "createOrder";
    }

//    @PostMapping("/updateOrderStatus/{id}")
//    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest request){
//        orderService.updateOrderStatus(id, request);
//        return ResponseEntity.ok(request);
//    }

    @PostMapping("/updateOrderStatus/{id}")
    public String updateOrderStatus(@PathVariable Long id, @ModelAttribute UpdateOrderStatusRequest request){
        orderService.updateOrderStatus(id, request);
        return "redirect:/api/orders/getAll";
    }

    @GetMapping("/updateOrderStatus/{id}")
    public String updateOrderStatusPage(@PathVariable Long id, Model model){
        UpdateOrderStatusRequest request = new UpdateOrderStatusRequest();
        model.addAttribute("order", request);
        model.addAttribute("id", id); // id'yi model nesnesine ekliyoruz
        return "updateOrderStatus";
    }

    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.POST)
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/api/orders/getAll";
    }

    @GetMapping("/getOrdersByUserId/{userId}")
    public String getOrdersByUserId(@PathVariable Long userId, Model model) {
        List<GetAllOrdersResponse> orders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders);
        return "adminOrders";
    }

}
