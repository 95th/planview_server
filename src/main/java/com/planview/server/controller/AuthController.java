package com.planview.server.controller;

import javax.validation.Valid;

import com.planview.server.entity.AuthResponse;
import com.planview.server.entity.LoginDetails;
import com.planview.server.entity.User;
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
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public AuthResponse login(@RequestBody @Valid LoginDetails loginDetails) {
        var user = this.authService.verifyLogin(loginDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        var token = this.jwtUtil.createToken(user);
        var resp = new AuthResponse();
        resp.setToken(token);
        resp.setRole(user.getRole());
        return resp;
    }

    @PostMapping("register")
    public void register(@RequestBody @Valid User user) {
        this.authService.registerUser(user);
    }
}
