package com.planview.server.service;

import javax.transaction.Transactional;

import com.planview.server.entity.LoginDetails;
import com.planview.server.entity.User;
import com.planview.server.entity.UserRole;
import com.planview.server.repos.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User loginUser(LoginDetails loginDetails) {
        var user = this.userRepo.findByUserName(loginDetails.getUsername());
        if (user == null) {
            return null;
        }

        if (user.isLocked()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Account is locked");
        }

        if (this.passwordEncoder.matches(loginDetails.getPassword(), user.getPassword())) {
            return user;
        }

        user.setFailedTries(user.getFailedTries() + 1);
        if (user.getFailedTries() >= 3) {
            user.setLocked(true);
        }

        this.userRepo.save(user);
        return null;
    }

    public User registerUser(User user) {
        if (this.userRepo.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (this.userRepo.existsByUserName(user.getUserName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        user.setRole(UserRole.USER);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepo.save(user);
    }

    public static int getCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = auth.getPrincipal();
        if (principal instanceof Integer) {
            return (int) principal;
        }

        return -1;
    }
}
