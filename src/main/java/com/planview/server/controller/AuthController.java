package com.planview.server.controller;

import javax.validation.Valid;

import com.planview.server.entity.LoginDetails;
import com.planview.server.repos.JwtRepo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final JwtRepo jwtRepo;

    public AuthController(JwtRepo userRepo) {
        this.jwtRepo = userRepo;
    }

    @PostMapping("login")
    public String login(@RequestBody @Valid LoginDetails loginDetails) {
        var token = this.jwtRepo.getToken(loginDetails);
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        return token;
    }
}
