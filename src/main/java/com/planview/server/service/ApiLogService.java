package com.planview.server.service;

import java.time.LocalDate;
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
        return this.apiLogRepo.save(log);
    }

    public List<ApiLog> getLogs(LocalDate startDate, LocalDate endDate) {
        var start = startDate.atStartOfDay();
        var end = endDate.plusDays(1).atStartOfDay();
        return this.apiLogRepo.findAllForDateRange(start, end);
    }

    public List<ApiLogAggregate> getLogsAggregate(LocalDate startDate, LocalDate endDate) {
        var start = startDate.atStartOfDay();
        var end = endDate.plusDays(1).atStartOfDay();
        return this.apiLogRepo.findAllForDateRangeAsAggregate(start, end);
    }
}
