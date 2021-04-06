package com.planview.server.repos;

import java.util.List;

import com.planview.server.entity.WorkAssignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkAssignmentRepo extends JpaRepository<WorkAssignment, Integer> {
    List<WorkAssignment> findAllByUserId(int userId);

    void deleteAllByUserId(int userId);
}
