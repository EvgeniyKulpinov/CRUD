package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.model.User;
import com.kulcorp.CRUD.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private UserService service;

    @GetMapping("/getUsers")
    public List<User> getAdminPage() {
        return service.getAll();
    }

    @GetMapping("/userForm/{id}")
    public User userForm(@PathVariable("id") Long id) {
        return service.getUserById(id);
    }

    @GetMapping(value = "/getAdmin")
    public User getUserPage(@AuthenticationPrincipal User user) {
        return user;
    }

    @PostMapping(value = "/user-create")
    public String createUser(@ModelAttribute("user") User user) {

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return "{\"status\":\"errorPassword\"}";
        }
        if (service.saveUser(user)) {
            return "{\"status\":\"errorName\"}";
        }
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {

        if (!service.userUpdate(user)) {
            return "{\"status\":\"errorName\"}";
        }
        return "{\"status\":\"success\"}";
    }
}
