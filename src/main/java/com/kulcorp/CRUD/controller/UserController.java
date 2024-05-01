package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.model.User;
import com.kulcorp.CRUD.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService service;

    @RequestMapping("/user")
    public String getCars(Model model) {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping("/user-create")
    private String createFromUser(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        service.add(user);
        return "redirect:/user";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        service.add(user);
        return "redirect:/user";
    }
}