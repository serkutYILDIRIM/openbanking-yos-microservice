package com.openbanking.yos.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OhvpsException.class)
    public ResponseEntity<ErrorResponse> handleOhvpsException(OhvpsException ex) {
        String message = ex.getMessage();

        HttpStatus status;
        String httpAcklm;

        if (message != null && (message.contains("InvalidASPSP") || message.contains("InvalidTPP"))) {
            status = HttpStatus.UNAUTHORIZED;
            httpAcklm = "Unauthorized";
        } else {
            status = HttpStatus.BAD_REQUEST;
            httpAcklm = "Bad Request";
        }

        ErrorResponse body = ErrorResponse.builder()
                .httpKod(status.value())
                .httpAcklm(httpAcklm)
                .morTnmKod(message)
                .morTnmAcklm(message)
                .zaman(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        ErrorResponse body = ErrorResponse.builder()
                .httpKod(HttpStatus.BAD_REQUEST.value())
                .httpAcklm("Bad Request")
                .morTnmKod("TR.OHVPS.Resource.InvalidFormat")
                .morTnmAcklm(detail)
                .zaman(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(body);
    }
}
