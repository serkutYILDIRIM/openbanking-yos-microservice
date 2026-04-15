package com.openbanking.yos.domain.card.controller;

import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.card.dto.response.CardListResponse;
import com.openbanking.yos.domain.card.dto.response.CardResponse;
import com.openbanking.yos.domain.card.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    private static final String X_REQUEST_ID = "req-123";
    private static final String X_ASPSP_CODE = "HHS01";
    private static final String X_TPP_CODE = "YOS01";
    private static final String X_JWS_SIGNATURE = "sig-123";
    private static final String X_RIZA_NO = "riza-123";
    private static final String KART_REF = "kart-456";

    private CardListResponse cardListResponse;
    private CardResponse cardResponse;

    @BeforeEach
    void setUp() {
        cardResponse = CardResponse.builder()
                .kartRef(KART_REF)
                .rizaNo(X_RIZA_NO)
                .build();

        cardListResponse = CardListResponse.builder()
                .kartlar(Collections.singletonList(cardResponse))
                .build();
    }

    @Test
    @DisplayName("GET /kartlar başarılı yanıt dönmeli")
    void getCards_ShouldReturnOk() {
        // Given
        when(cardService.getCards(eq(X_ASPSP_CODE), eq(X_TPP_CODE), eq(X_RIZA_NO)))
                .thenReturn(cardListResponse);

        // When
        ResponseEntity<CardListResponse> response = cardController.getCards(
                X_REQUEST_ID, X_ASPSP_CODE, X_TPP_CODE, X_JWS_SIGNATURE, X_RIZA_NO);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cardListResponse, response.getBody());
        verify(cardService).getCards(X_ASPSP_CODE, X_TPP_CODE, X_RIZA_NO);
    }

    @Test
    @DisplayName("GET /kartlar/{kartRef} başarılı yanıt dönmeli")
    void getCard_ShouldReturnOk() {
        // Given
        when(cardService.getCard(eq(KART_REF), eq(X_ASPSP_CODE), eq(X_TPP_CODE), eq(X_RIZA_NO)))
                .thenReturn(cardResponse);

        // When
        ResponseEntity<CardResponse> response = cardController.getCard(
                KART_REF, X_REQUEST_ID, X_ASPSP_CODE, X_TPP_CODE, X_JWS_SIGNATURE, X_RIZA_NO);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cardResponse, response.getBody());
        verify(cardService).getCard(KART_REF, X_ASPSP_CODE, X_TPP_CODE, X_RIZA_NO);
    }

    @Test
    @DisplayName("Servis hata fırlattığında hata fırlatmalı")
    void getCards_WhenServiceThrowsException_ShouldThrowSameException() {
        // Given
        when(cardService.getCards(anyString(), anyString(), anyString()))
                .thenThrow(new OhvpsException("TR.OHVPS.Resource.NotFound"));

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardController.getCards(X_REQUEST_ID, X_ASPSP_CODE, X_TPP_CODE, X_JWS_SIGNATURE, X_RIZA_NO));
        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }
}
