package com.autoassess.project.user.controller;

import com.autoassess.project.user.dto.LoginRequest;
import com.autoassess.project.user.dto.LoginResponse;
import com.autoassess.project.user.dto.RegisterRequest;
import com.autoassess.project.user.dto.RegisterResponse;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
