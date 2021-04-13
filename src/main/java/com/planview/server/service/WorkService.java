package com.planview.server.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.planview.server.entity.WorkAssignment;
import com.planview.server.entity.WorkItem;
import com.planview.server.entity.WorkType;
import com.planview.server.repos.WorkAssignmentRepo;
import com.planview.server.repos.WorkItemRepo;
import com.planview.server.repos.WorkTypeRepo;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class WorkService {
    private final WorkTypeRepo typeRepo;
    private final WorkItemRepo itemRepo;
    private final WorkAssignmentRepo assignmentRepo;

    public WorkService(WorkTypeRepo typeRepo, WorkItemRepo itemRepo, WorkAssignmentRepo assignmentRepo) {
        this.typeRepo = typeRepo;
        this.itemRepo = itemRepo;
        this.assignmentRepo = assignmentRepo;
    }

    public WorkType createType(WorkType type) {
        return this.typeRepo.save(type);
    }

    public List<WorkType> getTypes() {
        return this.typeRepo.findAll();
    }

    public Optional<WorkType> getTypeById(int id) {
        return this.typeRepo.findById(id);
    }

    public WorkItem createItem(WorkItem item) {
        return this.itemRepo.save(item);
    }

    public List<WorkItem> getItems() {
        return this.itemRepo.findAll();
    }

    public Optional<WorkItem> getItemById(int id) {
        return this.itemRepo.findById(id);
    }

    public List<WorkAssignment> createAssignments(List<WorkAssignment> assignments) {
        return this.assignmentRepo.saveAll(assignments);
    }

    public List<WorkAssignment> getAssignments() {
        var userId = AuthService.getCurrentUserId();
        return this.assignmentRepo.findAllByUserId(userId);
    }

    public Optional<WorkAssignment> getAssignmentById(int id) {
        return this.assignmentRepo.findById(id);
    }
}
