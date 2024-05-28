package com.kulcorp.CRUD.controller;

import com.kulcorp.CRUD.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @GetMapping(value = "/userInfo")
    public User getUserInfo(@AuthenticationPrincipal User user) {
        return user;
    }
}