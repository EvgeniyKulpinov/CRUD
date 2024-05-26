package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.model.User;
import com.kulcorp.CRUD.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user1";
    }

    @GetMapping("/registration")
    public String registration(User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("Error", "Пароли не совпадают");
            return "registration";
        }
        if (!service.saveUser(user)) {
            model.addAttribute("Error", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/user";
    }
}