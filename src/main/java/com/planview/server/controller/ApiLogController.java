package com.planview.server.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.ApiLog;
import com.planview.server.entity.ApiLogAggregate;
import com.planview.server.service.ApiLogService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ApiLog createLog(@RequestBody @Valid ApiLog log) {
        return this.apiLogService.createLog(log);
    }

    @GetMapping
    public List<ApiLog> getLogs(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return this.apiLogService.getLogs(startDate, endDate);
    }

    @GetMapping("aggregate")
    public List<ApiLogAggregate> getLogsAggregate(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return this.apiLogService.getLogsAggregate(startDate, endDate);
    }

}
