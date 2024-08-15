package com.yilmaz.ECommerce.controller.userController;

import com.yilmaz.ECommerce.model.dto.requests.paymentRequests.PaymentRequest;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.service.abstracts.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users/payment")
public class UsersPaymentController {
    private final PaymentService paymentService;

    public UsersPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public String showPaymentForm(Model model) {
        PaymentRequest paymentRequest = new PaymentRequest();
        model.addAttribute("payment", paymentRequest);
        return "paymentForm";
    }

    @PostMapping("/pay")
    public String processPayment(@ModelAttribute PaymentRequest request, HttpSession session) {
        Order activeOrder = (Order) session.getAttribute("activeOrder");
        request.setOrderId(activeOrder.getId());
        paymentService.pay(request);
        session.removeAttribute("activeOrder");
        return "redirect:/api/users/products/getAll";
    }
}