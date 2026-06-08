package com.budget.budget_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex)
    {
        return ResponseEntity.status(404).body(error(ex.getMessage()));

    } 

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflict(ConflictException ex)
    {
        return ResponseEntity.status(404).body(error(ex.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
        .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(404).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex)
    {
        return ResponseEntity.status(500).body(error("Wystąpił nieoczenikway błað"));
    }

    private Map<String, String> error(String message)
    {
        Map<String, String> map = new HashMap<>();
        map.put("error", message);
        return map;
    }
}
