package com.planview.server.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.planview.server.entity.WorkAssignment;
import com.planview.server.entity.WorkItem;
import com.planview.server.entity.WorkType;
import com.planview.server.service.WorkService;

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
public class WorkController {
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping("type")
    public WorkType createType(@RequestBody @Valid WorkType type) {
        return this.workService.createType(type);
    }

    @GetMapping("type")
    public List<WorkType> getTypes() {
        return this.workService.getTypes();
    }

    @GetMapping("type/{id}")
    public Optional<WorkType> getTypeById(@PathVariable int id) {
        return this.workService.getTypeById(id);
    }

    @PostMapping("item")
    public WorkItem createItem(@RequestBody @Valid WorkItem item) {
        return this.workService.createItem(item);
    }

    @GetMapping("item")
    public List<WorkItem> getItems() {
        return this.workService.getItems();
    }

    @GetMapping("item/{id}")
    public Optional<WorkItem> getItemById(@PathVariable int id) {
        return this.workService.getItemById(id);
    }

    @PostMapping("assign")
    public List<WorkAssignment> createAssignments(@RequestBody @Valid List<WorkAssignment> assignments) {
        return this.workService.createAssignments(assignments);
    }

    @GetMapping("assign")
    public List<WorkAssignment> getAssignments() {
        return this.workService.getAssignments();
    }

    @GetMapping("assign/{id}")
    public Optional<WorkAssignment> getAssignmentById(@PathVariable int id) {
        return this.workService.getAssignmentById(id);
    }

}
