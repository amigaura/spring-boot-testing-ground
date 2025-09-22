package com.testing.ground.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        error.setMessage("Duplicate entry: " + extractConstraintDetail(ex));
        error.setPath(request.getRequestURI());
        return error;
    }

    private String extractConstraintDetail(DataIntegrityViolationException ex) {
        String msg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        if (msg != null && msg.contains("duplicate key value")) {
            int idx = msg.indexOf("Detail:");
            return idx > 0 ? msg.substring(idx).trim() : msg;
        }
        return "Unique constraint violation";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage()));
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Failed");
        error.setMessage("Validation errors occurred");
        error.setPath(request.getRequestURI());
        error.setValidationErrors(
                ex.getBindingResult().getFieldErrors().stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage
                        ))
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // generic fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOtherExceptions(Exception ex) {
        String message = ex.getMessage() != null ? ex.getMessage() : "Unknown error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", message));
    }
}
