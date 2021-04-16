package com.planview.server.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.planview.server.entity.ApiLog;
import com.planview.server.entity.ApiLogAggregate;
import com.planview.server.service.ApiLogService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/log")
public class ApiLogController {
    private final ApiLogService apiLogService;

    public ApiLogController(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @GetMapping
    public List<ApiLog> getLogs(
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate) {
        endDate = endDate.plusDays(1);
        return this.apiLogService.getLogs(startDate, endDate);
    }

    @GetMapping("aggregate")
    public List<ApiLogAggregate> getLogsAggregate(
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate) {
        endDate = endDate.plusDays(1);
        return this.apiLogService.getLogsAggregate(startDate, endDate);
    }

}
