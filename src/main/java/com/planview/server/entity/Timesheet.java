package com.planview.server.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false, referencedColumnName = "id")
    private WorkAssignment assignment;

    @NotNull
    @Column(name = "week_start_date", nullable = false, columnDefinition = "DATE")
    private LocalDate weekStartDate;

    @Column(name = "hours_monday")
    private int hoursMonday;

    @Column(name = "hours_tuesday")
    private int hoursTuesday;

    @Column(name = "hours_wednesday")
    private int hoursWednesday;

    @Column(name = "hours_thursday")
    private int hoursThursday;

    @Column(name = "hours_friday")
    private int hoursFriday;

    @NotNull
    @Column(name = "last_updated", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime lastUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(WorkAssignment assignment) {
        this.assignment = assignment;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public int getHoursMonday() {
        return hoursMonday;
    }

    public void setHoursMonday(int hoursMonday) {
        this.hoursMonday = hoursMonday;
    }

    public int getHoursTuesday() {
        return hoursTuesday;
    }

    public void setHoursTuesday(int hoursTuesday) {
        this.hoursTuesday = hoursTuesday;
    }

    public int getHoursWednesday() {
        return hoursWednesday;
    }

    public void setHoursWednesday(int hoursWednesday) {
        this.hoursWednesday = hoursWednesday;
    }

    public int getHoursThursday() {
        return hoursThursday;
    }

    public void setHoursThursday(int hoursThursday) {
        this.hoursThursday = hoursThursday;
    }

    public int getHoursFriday() {
        return hoursFriday;
    }

    public void setHoursFriday(int hoursFriday) {
        this.hoursFriday = hoursFriday;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
