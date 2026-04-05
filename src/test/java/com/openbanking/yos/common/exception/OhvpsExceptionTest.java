package com.openbanking.yos.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OhvpsExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        String message = "TR.OHVPS.Resource.NotFound";
        OhvpsException exception = new OhvpsException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        String message = "Internal Server Error";
        Throwable cause = new RuntimeException("Original error");
        OhvpsException exception = new OhvpsException(message, cause);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
