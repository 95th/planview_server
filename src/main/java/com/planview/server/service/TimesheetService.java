package com.planview.server.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import com.planview.server.entity.Timesheet;
import com.planview.server.entity.TimesheetView;
import com.planview.server.repos.TimesheetRepo;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class TimesheetService {
    private final TimesheetRepo timesheetRepo;

    public TimesheetService(TimesheetRepo timesheetRepo) {
        this.timesheetRepo = timesheetRepo;
    }

    public List<Timesheet> saveTimesheets(List<Timesheet> timesheets) {
        return this.timesheetRepo.saveAll(timesheets);
    }

    public List<Timesheet> getTimesheetsInRange(LocalDate weekStartDate) {
        var userId = AuthService.getCurrentUserId();
        return this.timesheetRepo.findAllForWeek(userId, weekStartDate);
    }

    public List<TimesheetView> getAllTimesheets() {
        return this.timesheetRepo.findAllSubmitted();
    }
}
