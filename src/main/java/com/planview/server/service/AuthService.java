package com.planview.server.service;

import com.planview.server.entity.LoginDetails;
import com.planview.server.entity.User;
import com.planview.server.repos.UserRepo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(LoginDetails loginDetails) {
        var user = this.userRepo.findByUserName(loginDetails.getUsername());
        if (!this.passwordEncoder.matches(loginDetails.getPassword(), user.getPassword())) {
            return null;
        }

        return user;
    }
}
