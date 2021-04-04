package com.planview.server.service;

import com.planview.server.repos.UserRepo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlanviewUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public PlanviewUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return User.builder()
                   .username(user.getUserName())
                   .password(user.getPassword())
                   .roles(user.getRole().name())
                   .accountLocked(user.isLocked())
                   .build();
    }

}
