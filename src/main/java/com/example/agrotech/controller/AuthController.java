package com.example.agrotech.controller;

import com.example.agrotech.model.User;
import com.example.agrotech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        System.out.println("USER AFTER REGISTER:" + user.getUsername() + "Password:" + user.getPassword());
        userService.saveUser(user);
        return "User has been created";
    }
}
