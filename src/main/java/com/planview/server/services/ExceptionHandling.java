package com.planview.server.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandling {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ DataIntegrityViolationException.class })
    public void handleIntegrityViolation() {
    }
}
