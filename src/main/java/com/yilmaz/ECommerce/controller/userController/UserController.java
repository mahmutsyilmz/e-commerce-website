package com.yilmaz.ECommerce.controller.userController;

import com.yilmaz.ECommerce.service.concretes.EmailSenderService;
import com.yilmaz.ECommerce.service.concretes.UserManager;
import com.yilmaz.ECommerce.model.concretes.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final EmailSenderService emailSenderService;
    private final UserManager userManager;

    @Autowired
    public UserController(EmailSenderService emailSenderService, UserManager userManager) {
        this.emailSenderService = emailSenderService;
        this.userManager = userManager;
    }




    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        userManager.register(user);
        return "redirect:/api/users/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session){
        User loggedInUser = userManager.login(user);
        if (loggedInUser != null && loggedInUser.isActive()){
            session.setAttribute("user", loggedInUser);
            return "redirect:/api/users/products/getAll";
        }
        return "redirect:/api/users/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }


    @GetMapping("/reg/{key}")
    public String regOk (@PathVariable String key){
        if (userManager.findByKey(key)){
            return "redirect:/api/users/login";
        }
        return "redirect:/register";
    }




}
