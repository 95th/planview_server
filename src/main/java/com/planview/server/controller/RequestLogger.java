package com.planview.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planview.server.entity.ApiLog;
import com.planview.server.service.ApiLogService;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Component
public class RequestLogger implements AsyncHandlerInterceptor {
    private final ApiLogService apiLogService;

    public RequestLogger(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var uri = request.getRequestURI();
        var query = request.getQueryString();
        var log = new ApiLog();
        log.setUrl(uri);
        log.setQueryParams(query);
        this.apiLogService.createLog(log);
        return true;
    }

}
