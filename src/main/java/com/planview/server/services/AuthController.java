package com.planview.server.services;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @PostMapping("login")
    public String login(@RequestBody @Valid LoginDetails loginDetails) {
        return loginDetails.getUsername();
    }
}
