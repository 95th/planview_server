package com.planview.server.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.ApiLog;
import com.planview.server.entity.ApiLogAggregate;
import com.planview.server.repos.ApiLogRepo;

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
    private final ApiLogRepo apiLogRepo;

    public ApiLogController(ApiLogRepo apiLogRepo) {
        this.apiLogRepo = apiLogRepo;
    }

    @PostMapping
    public ApiLog createLog(@RequestBody @Valid ApiLog log) {
        return this.apiLogRepo.save(log);
    }

    @GetMapping
    public List<ApiLog> getLogs(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return this.apiLogRepo.findAllForDateRange(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
    }

    @GetMapping("aggregate")
    public List<ApiLogAggregate> getLogsAggregate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return this.apiLogRepo.findAllForDateRangeAsAggregate(startDate.atStartOfDay(),
                endDate.plusDays(1).atStartOfDay());
    }

}
