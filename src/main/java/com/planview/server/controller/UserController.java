package com.planview.server.controller;

import javax.validation.Valid;

import com.planview.server.entity.User;
import com.planview.server.entity.UserRole;
import com.planview.server.repos.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("register")
    public User register(@RequestBody @Valid User user) {
        if (this.userRepo.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (this.userRepo.existsByUserName(user.getUserName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        user.setRole(UserRole.USER);
        return this.userRepo.save(user);
    }

    @PutMapping("{userId}/make-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public User makeAdmin(@PathVariable int userId) {
        var userOpt = this.userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var user = userOpt.get();
        user.setRole(UserRole.ADMIN);
        return this.userRepo.save(user);
    }

    @PutMapping("{userId}/unlock")
    @PreAuthorize("hasRole('ADMIN')")
    public User unlock(@PathVariable int userId) {
        var userOpt = this.userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var user = userOpt.get();
        user.setLocked(false);
        return this.userRepo.save(user);
    }
}
