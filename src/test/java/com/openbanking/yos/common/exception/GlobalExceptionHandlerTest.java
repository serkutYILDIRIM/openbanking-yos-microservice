package com.openbanking.yos.common.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldHandleOhvpsExceptionWithBadRequest() {
        OhvpsException ex = new OhvpsException("Test error message");
        ResponseEntity<ErrorResponse> response = handler.handleOhvpsException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getHttpKod());
        assertEquals("Bad Request", response.getBody().getHttpAcklm());
        assertEquals("Test error message", response.getBody().getMorTnmKod());
        assertNotNull(response.getBody().getZaman());
    }

    @Test
    void shouldHandleOhvpsExceptionWithUnauthorized() {
        OhvpsException ex = new OhvpsException("InvalidASPSP error");
        ResponseEntity<ErrorResponse> response = handler.handleOhvpsException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(401, response.getBody().getHttpKod());
        assertEquals("Unauthorized", response.getBody().getHttpAcklm());
    }

    @Test
    void shouldHandleValidationException() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "must not be null");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<ErrorResponse> response = handler.handleValidationException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getHttpKod());
        assertEquals("Bad Request", response.getBody().getHttpAcklm());
        assertEquals("field: must not be null", response.getBody().getMorTnmAcklm());
    }
}
