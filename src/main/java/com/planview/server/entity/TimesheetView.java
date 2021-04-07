package com.planview.server.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimesheetView {
    private final String userName;
    private final String workItemName;
    private final LocalDate weekStartDate;
    private final LocalDateTime lastUpdated;

    public TimesheetView(String userName, String workItemName, LocalDate weekStartDate,
            LocalDateTime lastUpdated) {
        this.userName = userName;
        this.workItemName = workItemName;
        this.weekStartDate = weekStartDate;
        this.lastUpdated = lastUpdated;
    }

    public String getUserName() {
        return userName;
    }

    public String getWorkItemName() {
        return workItemName;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

}
