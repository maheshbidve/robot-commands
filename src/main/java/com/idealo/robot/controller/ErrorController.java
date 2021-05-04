package com.idealo.robot.controller;

import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.convert.ConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";

    @NonNull
    private final ErrorAttributes errorAttributes;

    @Autowired
    public ErrorController(@NonNull ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException() {
        final var object = Map.<String, Object>of(
            "status", HttpStatus.NOT_FOUND.value(),
            "error", "Not Found",
            "timestamp", new Date()
        );
        return new ResponseEntity<>(object, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException() {
        final var object = Map.<String, Object>of(
            "status", HttpStatus.NOT_ACCEPTABLE.value(),
            "error", "Illegal argument passed in input",
            "timestamp", new Date()
        );
        return new ResponseEntity<>(object, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(ConversionException ex) {
        if (ex.getMostSpecificCause() instanceof IllegalArgumentException) {
            return handleIllegalArgumentException();
        }
        throw ex;
    }

}