package com.planview.server.repos;

import java.time.LocalDateTime;
import java.util.List;

import com.planview.server.entity.ApiLog;
import com.planview.server.entity.ApiLogAggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApiLogRepo extends JpaRepository<ApiLog, Integer> {
    @Query(value = "select t from ApiLog t where t.timestamp >= :start and t.timestamp < :end")
    List<ApiLog> findAllForDateRange(LocalDateTime start, LocalDateTime end);

    @Query(value = "select new com.planview.server.entity.ApiLogAggregate(t.userId, t.url, count(1))"
            + " from ApiLog t where t.timestamp >= :start and t.timestamp < :end group by t.userId, t.url"
            + " order by t.userId, count(1) desc, t.url")
    List<ApiLogAggregate> findAllForDateRangeAsAggregate(LocalDateTime start, LocalDateTime end);
}
