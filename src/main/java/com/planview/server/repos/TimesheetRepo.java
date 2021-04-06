package com.planview.server.repos;

import java.time.LocalDate;
import java.util.List;

import com.planview.server.entity.Timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TimesheetRepo extends JpaRepository<Timesheet, Integer> {

    @Query(value = "select t from Timesheet t"
            + " where t.assignment.userId = :userId and t.weekStartDate = :weekStartDate order by t.assignment.id")
    List<Timesheet> findAllForWeek(int userId, LocalDate weekStartDate);

    @Modifying
    @Query(value = "delete from Timesheet t where t in (select q from Timesheet q where q.assignment.userId = :userId)")
    void deleteAllByUserId(int userId);
}
