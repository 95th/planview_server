package com.planview.server.repos;

import com.planview.server.entity.WorkAssignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkAssignmentRepo extends JpaRepository<WorkAssignment, Integer> {

}
