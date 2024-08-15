package com.yilmaz.ECommerce.controller.userController;

import com.yilmaz.ECommerce.model.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.model.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.model.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.User;
import com.yilmaz.ECommerce.service.abstracts.CategoryService;
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
    private final CategoryService categoryService;

    public UsersProductController(ProductService productService, OrderService orderService, OrderItemService orderItemService, CategoryService categoryService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.categoryService = categoryService;
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
        model.addAttribute("categories", categoryService.getAllCategories());
        return "usersProducts";
    }

    @PostMapping("/createOrderItem")
    public String createOrderItem(@ModelAttribute CreateOrderItemRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        request.setUserId(user.getId());

        // Kullanıcının oturumunda aktif bir sipariş olup olmadığını kontrol edin
        Order activeOrder = (Order) session.getAttribute("activeOrder");
        // Sipariş öğesini aktif siparişe ekleyin
        request.setOrderId(activeOrder.getId());
        orderItemService.createOrderItem(request);

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

    @GetMapping("/searchByName")
    public String searchProduct(@RequestParam String name, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("products", productService.getProductsByName(name));
        return "usersProducts";
    }



}
