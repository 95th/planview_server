package com.planview.server.controller;

import java.util.List;

import com.planview.server.entity.UserView;
import com.planview.server.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserView> getUsersExceptMe() {
        return this.userService.findAllExceptMe();
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserView updateUser(@RequestBody UserView view) {
        return this.userService.updateUser(view);
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable int userId) {
        this.userService.deleteUser(userId);
    }
}
