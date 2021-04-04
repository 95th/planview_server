package com.planview.server.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false, referencedColumnName = "id")
    private WorkAssignment assignment;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    private int hours;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

}
