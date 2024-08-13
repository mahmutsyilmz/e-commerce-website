package com.yilmaz.ECommerce.controller.userController;

import com.yilmaz.ECommerce.model.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.model.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.model.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.User;
import com.yilmaz.ECommerce.service.abstracts.OrderItemService;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import com.yilmaz.ECommerce.service.abstracts.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users/products")
public class UsersProductController {
    private final ProductService productService;
    private final OrderService orderService;

    private final OrderItemService orderItemService;

    public UsersProductController(ProductService productService, OrderService orderService, OrderItemService orderItemService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }


    @GetMapping("/getAll")
    public String getAllProducts(Model model, HttpSession session) {
        // Oturumda aktif bir sipariş olup olmadığını kontrol et
        Order activeOrder = (Order) session.getAttribute("activeOrder");
        User user = (User) session.getAttribute("user");

        if (activeOrder == null) {
            // Eğer aktif bir sipariş yoksa, yeni bir tane oluştur
            CreateOrderRequest request = new CreateOrderRequest();
            request.setUserId(user.getId());
            activeOrder = orderService.createOrder(request);
            // Yeni siparişi oturumdaki aktif sipariş olarak ayarla
            session.setAttribute("activeOrder", activeOrder);
        } else {
            // Eğer aktif bir sipariş varsa, onu ve ilişkili sipariş öğelerini aynı Hibernate oturumunda yükle
            activeOrder = orderService.findOrderWithOrderItems(activeOrder.getId());
            session.setAttribute("activeOrder", activeOrder);
        }
        model.addAttribute("activeOrder", activeOrder);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("user", user);
        return "usersProducts";
    }

    @PostMapping("/createOrderItem")
    public String createOrderItem(@ModelAttribute CreateOrderItemRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        request.setUserId(user.getId());

        // Kullanıcının oturumunda aktif bir sipariş olup olmadığını kontrol edin
        Order activeOrder = (Order) session.getAttribute("activeOrder");
        if (activeOrder == null) {
            // Aktif bir sipariş yoksa, hata mesajı döndür
            return "redirect:/error?message=No active order found. Please create an order first.";
        }

        // Sipariş öğesini aktif siparişe ekleyin
        request.setOrderId(activeOrder.getId());
        orderItemService.createOrderItem(request);

        return "redirect:/api/users/products/getAll";
    }


    @PostMapping("/payOrder")
    public String payOrder(HttpSession session) {
        // Kullanıcının oturumunda aktif bir sipariş olup olmadığını kontrol edin
        Order activeOrder = (Order) session.getAttribute("activeOrder");

        // Siparişi öde
        //orderService.payOrder(activeOrder.getId());

        // Oturumdaki aktif siparişi kaldır
        session.removeAttribute("activeOrder");

        return "redirect:/api/users/products/getAll";
    }

    @GetMapping("/getAllOrdersByUserId")
    public String getAllOrdersByUserId(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<GetAllOrdersResponse> orders = orderService.getOrdersByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "usersOrders";
    }

    @PostMapping("/deleteOrderItem")
    public String deleteOrderItem(@RequestParam Long orderItemId) {

        // Sipariş öğesini sil
        orderItemService.deleteOrderItem(orderItemId);

        return "redirect:/api/users/products/getAll";
    }





}
