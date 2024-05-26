package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.model.User;
import com.kulcorp.CRUD.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/deleteForm/{id}")
    public User getDeleteForm(@PathVariable("id") Long id) {
        return service.getUserById(id);
    }

//    @GetMapping("/user-create")
//    private String createFromUser(User user) {
//        return "admin";
//    }

    @RequestMapping(value = "/user-create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") User user) {

//        if (!user.getPassword().equals(user.getPasswordConfirm())) {
//            model.addAttribute("Error", "Пароли не совпадают");
//        }
        if (!service.saveUser(user)) {
//            model.addAttribute("Error", "Пользователь с таким именем уже существует");
        }
        return "redirect:/admin";//!!!!
    }

    @RequestMapping("/user-delete/{id}")
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
