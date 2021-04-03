package com.planview.server.repos;

import java.time.LocalDate;
import java.util.List;

import com.planview.server.entity.RequestLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestLogRepo extends JpaRepository<RequestLog, Integer> {
    @Query(value = "select * from request_logs where timestamp >= :start_date and trunc(timestamp) <= :end_date", nativeQuery = true)
    List<RequestLog> findAllForDateRange(@Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate);
}
