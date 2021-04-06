package com.planview.server.repos;

import java.util.List;

import com.planview.server.entity.User;
import com.planview.server.entity.UserView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    @Query("select new com.planview.server.entity.UserView(u) from User u where u.id != :userId order by u.id")
    List<UserView> findAllExcept(int userId);
}
