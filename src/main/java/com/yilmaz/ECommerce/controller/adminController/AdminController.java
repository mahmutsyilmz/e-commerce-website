package com.yilmaz.ECommerce.controller.adminController;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/home")
    public String adminHomePage(HttpSession session) {
        if (session.getAttribute("isAdmin") != null) {
            return "adminHome";
        }else {
            return "redirect:/api/users/login";
        }
    }
}
