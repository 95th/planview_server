package com.planview.server.repos;

import java.time.LocalDate;
import java.util.List;

import com.planview.server.entity.Timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimesheetRepo extends JpaRepository<Timesheet, Integer> {

    @Query(value = "select t from Timesheet t"
            + " where t.assignment.user.id = :userId and t.date >= :start and t.date < :end order by t.date")
    List<Timesheet> findAllInDateRange(int userId, LocalDate start, LocalDate end);
}
