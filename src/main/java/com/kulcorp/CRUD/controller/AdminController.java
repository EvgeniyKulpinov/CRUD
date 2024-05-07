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
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private UserService service;

    @RequestMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", service.getAll());
        return "admin";
    }

    @GetMapping("/user-create")
    private String createFromUser(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user, Model model) {

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("Error", "Пароли не совпадают");
            return "user-create";
        }
        if (!service.saveUser(user)) {
            model.addAttribute("Error", "Пользователь с таким именем уже существует");
            return "user-create";
        }
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        service.userUpdate(user);
        return "redirect:/admin";
    }
}
