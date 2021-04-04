package com.planview.server.repos;

import java.time.LocalDateTime;
import java.util.List;

import com.planview.server.entity.RequestLog;
import com.planview.server.entity.RequestLogAggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestLogRepo extends JpaRepository<RequestLog, Integer> {
    @Query(value = "select r from RequestLog r where r.timestamp >= :start and r.timestamp < :end")
    List<RequestLog> findAllForDateRange(LocalDateTime start, LocalDateTime end);

    @Query(value = "select new com.planview.server.entity.RequestLogAggregate(r.userId, r.url, count(1))"
            + " from RequestLog r where r.timestamp >= :start and r.timestamp < :end group by r.userId, r.url"
            + " order by r.userId, count(1) desc, r.url")
    List<RequestLogAggregate> findAllForDateRangeAsAggregate(LocalDateTime start, LocalDateTime end);
}
