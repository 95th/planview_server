package com.planview.server.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.transaction.Transactional;

import com.planview.server.entity.ApiLog;
import com.planview.server.entity.ApiLogAggregate;
import com.planview.server.repos.ApiLogRepo;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class ApiLogService {
    private final ApiLogRepo apiLogRepo;

    public ApiLogService(ApiLogRepo apiLogRepo) {
        this.apiLogRepo = apiLogRepo;
    }

    public ApiLog createLog(ApiLog log) {
        log.setUserId(AuthService.getCurrentUserId());
        log.setTimestamp(LocalDateTime.now(ZoneOffset.UTC));
        return this.apiLogRepo.save(log);
    }

    public List<ApiLog> getLogs(LocalDateTime start, LocalDateTime end) {
        return this.apiLogRepo.findAllForDateRange(start, end);
    }

    public List<ApiLogAggregate> getLogsAggregate(LocalDateTime start, LocalDateTime end) {
        return this.apiLogRepo.findAllForDateRangeAsAggregate(start, end);
    }
}
