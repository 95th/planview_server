package com.planview.server.controller;

import javax.validation.Valid;

import com.planview.server.entity.LoginDetails;
import com.planview.server.jwt.JwtUtil;
import com.planview.server.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public String login(@RequestBody @Valid LoginDetails loginDetails) {
        var user = this.authService.getUser(loginDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return JwtUtil.createToken(user);
    }
}
