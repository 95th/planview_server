package com.planview.server.repos;

import java.time.LocalDate;
import java.util.List;

import com.planview.server.entity.Timesheet;
import com.planview.server.entity.TimesheetView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TimesheetRepo extends JpaRepository<Timesheet, Integer> {

    @Query(value = "select t from Timesheet t"
            + " where t.assignment.userId = :userId and t.weekStartDate = :weekStartDate order by t.assignment.id")
    List<Timesheet> findAllForWeek(int userId, LocalDate weekStartDate);

    @Query(value = "select new com.planview.server.entity.TimesheetView(u.userName, t.assignment.workItem.name, t.weekStartDate, t.lastUpdated)"
            + " from Timesheet t join User u on t.assignment.userId = u.id")
    List<TimesheetView> findAllSubmitted();

    @Modifying
    @Query(value = "delete from Timesheet t where t in (select q from Timesheet q where q.assignment.userId = :userId)")
    void deleteAllByUserId(int userId);
}
