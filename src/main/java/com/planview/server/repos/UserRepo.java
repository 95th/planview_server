package com.planview.server.repos;

import com.planview.server.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    boolean existsByUserName(String userName);
}
