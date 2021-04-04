package com.planview.server.repos;

import com.planview.server.entity.WorkItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkItemRepo extends JpaRepository<WorkItem, Integer> {

}
