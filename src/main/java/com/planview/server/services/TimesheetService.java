package com.planview.server.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.Timesheet;
import com.planview.server.repos.TimesheetRepo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/timesheet")
public class TimesheetService {
    private final TimesheetRepo timesheetRepo;

    public TimesheetService(TimesheetRepo timesheetRepo) {
        this.timesheetRepo = timesheetRepo;
    }

    @PostMapping
    public List<Timesheet> createTimesheets(@RequestBody @Valid List<Timesheet> timesheets) {
        return this.timesheetRepo.saveAll(timesheets);
    }

    @GetMapping
    public List<Timesheet> getTimesheetsInRange(@RequestParam int userId,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return this.timesheetRepo.findAllInDateRange(userId, startDate, endDate.plusDays(1));
    }

    @PutMapping
    public List<Timesheet> updateTimesheets(@RequestBody @Valid List<Timesheet> timesheets) {
        return this.timesheetRepo.saveAll(timesheets);
    }

}
