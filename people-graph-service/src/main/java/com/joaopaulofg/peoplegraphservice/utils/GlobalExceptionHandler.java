package com.joaopaulofg.peoplegraphservice.utils;

import com.joaopaulofg.peoplegraphservice.utils.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        return getMapResponseEntityNotFound(ex.getMessage(), ex);
    }

    private ResponseEntity<Map<String, Object>> getMapResponseEntityNotFound(String message, Object ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", message);
        body.put("exception", ex.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
