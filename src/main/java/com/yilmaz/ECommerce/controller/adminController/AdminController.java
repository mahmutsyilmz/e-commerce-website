package com.yilmaz.ECommerce.controller.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/home")
    public String adminHomePage() {
        return "adminHome";
    }
}
