package com.planview.server.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.Timesheet;
import com.planview.server.entity.TimesheetView;
import com.planview.server.repos.TimesheetRepo;
import com.planview.server.service.AuthService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/timesheet")
public class TimesheetController {
    private final TimesheetRepo timesheetRepo;

    public TimesheetController(TimesheetRepo timesheetRepo) {
        this.timesheetRepo = timesheetRepo;
    }

    @PostMapping
    public List<Timesheet> createTimesheets(@RequestBody @Valid List<Timesheet> timesheets) {
        return this.timesheetRepo.saveAll(timesheets);
    }

    @GetMapping
    public List<Timesheet> getTimesheetsInRange(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate weekStartDate) {
        var userId = AuthService.getCurrentUserId();
        return this.timesheetRepo.findAllForWeek(userId, weekStartDate);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TimesheetView> getAllTimesheets() {
        return this.timesheetRepo.findAllSubmitted();
    }

}
