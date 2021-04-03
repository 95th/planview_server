package com.planview.server.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.planview.server.entity.RequestLog;
import com.planview.server.repos.RequestLogRepo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/request-log")
public class RequestLogService {
    private final RequestLogRepo requestLogRepo;

    public RequestLogService(RequestLogRepo requestLogRepo) {
        this.requestLogRepo = requestLogRepo;
    }

    @PostMapping
    public RequestLog createLog(@RequestBody @Valid RequestLog log) {
        return this.requestLogRepo.save(log);
    }

    @GetMapping
    public List<RequestLog> getLogs(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.requestLogRepo.findAllForDateRange(startDate, endDate);
    }

}
