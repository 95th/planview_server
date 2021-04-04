package com.planview.server.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.ApiLog;
import com.planview.server.entity.ApiLogAggregate;
import com.planview.server.repos.ApiLogRepo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/log")
public class ApiLogService {
    private final ApiLogRepo requestLogRepo;

    public ApiLogService(ApiLogRepo requestLogRepo) {
        this.requestLogRepo = requestLogRepo;
    }

    @PostMapping
    public ApiLog createLog(@RequestBody @Valid ApiLog log) {
        return this.requestLogRepo.save(log);
    }

    @GetMapping
    public List<ApiLog> getLogs(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.requestLogRepo.findAllForDateRange(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
    }

    @GetMapping("aggregate")
    public List<ApiLogAggregate> getLogsAggregate(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.requestLogRepo.findAllForDateRangeAsAggregate(startDate.atStartOfDay(),
                endDate.plusDays(1).atStartOfDay());
    }

}
