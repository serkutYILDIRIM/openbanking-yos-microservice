package com.openbanking.yos.common.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorResponseTest {

    @Test
    void shouldCreateErrorResponseUsingBuilder() {
        int httpKod = 404;
        String httpAcklm = "Not Found";
        String morTnmKod = "TR.OHVPS.Resource.NotFound";
        String morTnmAcklm = "Kaynak bulunamadı";
        LocalDateTime zaman = LocalDateTime.now();

        ErrorResponse response = ErrorResponse.builder()
                .httpKod(httpKod)
                .httpAcklm(httpAcklm)
                .morTnmKod(morTnmKod)
                .morTnmAcklm(morTnmAcklm)
                .zaman(zaman)
                .build();

        assertNotNull(response);
        assertEquals(httpKod, response.getHttpKod());
        assertEquals(httpAcklm, response.getHttpAcklm());
        assertEquals(morTnmKod, response.getMorTnmKod());
        assertEquals(morTnmAcklm, response.getMorTnmAcklm());
        assertEquals(zaman, response.getZaman());
    }

    @Test
    void shouldCreateErrorResponseUsingNoArgsConstructor() {
        ErrorResponse response = new ErrorResponse();
        int httpKod = 500;
        response.setHttpKod(httpKod);

        assertNotNull(response);
        assertEquals(httpKod, response.getHttpKod());
    }
}
