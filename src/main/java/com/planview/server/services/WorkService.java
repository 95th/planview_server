package com.planview.server.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.planview.server.entity.WorkAssignment;
import com.planview.server.entity.WorkItem;
import com.planview.server.entity.WorkType;
import com.planview.server.repos.WorkAssignmentRepo;
import com.planview.server.repos.WorkItemRepo;
import com.planview.server.repos.WorkTypeRepo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/work")
@PreAuthorize("hasRole('ADMIN')")
public class WorkService {
    private final WorkTypeRepo typeRepo;
    private final WorkItemRepo itemRepo;
    private final WorkAssignmentRepo assignmentRepo;

    public WorkService(WorkTypeRepo typeRepo, WorkItemRepo itemRepo, WorkAssignmentRepo assignmentRepo) {
        this.typeRepo = typeRepo;
        this.itemRepo = itemRepo;
        this.assignmentRepo = assignmentRepo;
    }

    @PostMapping("type")
    public WorkType createType(@RequestBody @Valid WorkType type) {
        return this.typeRepo.save(type);
    }

    @GetMapping("type")
    public List<WorkType> getTypes() {
        return this.typeRepo.findAll();
    }

    @GetMapping("type/{id}")
    public Optional<WorkType> getTypeById(@PathVariable int id) {
        return this.typeRepo.findById(id);
    }

    @PostMapping("item")
    public WorkItem createItem(@RequestBody @Valid WorkItem item) {
        return this.itemRepo.save(item);
    }

    @GetMapping("item")
    public List<WorkItem> getItems() {
        return this.itemRepo.findAll();
    }

    @GetMapping("item/{id}")
    public Optional<WorkItem> getItemById(@PathVariable int id) {
        return this.itemRepo.findById(id);
    }

    @PostMapping("assign")
    public WorkAssignment createAssignment(@RequestBody @Valid WorkAssignment assignment) {
        return this.assignmentRepo.save(assignment);
    }

    @GetMapping("assign")
    public List<WorkAssignment> getAssignments() {
        return this.assignmentRepo.findAll();
    }

    @GetMapping("assign/{id}")
    public Optional<WorkAssignment> getAssignmentById(@PathVariable int id) {
        return this.assignmentRepo.findById(id);
    }

}
