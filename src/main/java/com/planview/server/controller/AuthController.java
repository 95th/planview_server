package com.planview.server.controller;

import javax.validation.Valid;

import com.planview.server.entity.LoginDetails;
import com.planview.server.entity.User;
import com.planview.server.repos.UserRepo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserRepo userRepo;

    public AuthController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("login")
    public User login(@RequestBody @Valid LoginDetails loginDetails) {
        var user = this.userRepo.findByUserName(loginDetails.getUsername());
        return user;
    }
}
