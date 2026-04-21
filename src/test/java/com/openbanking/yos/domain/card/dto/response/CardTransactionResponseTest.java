package com.openbanking.yos.domain.card.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTransactionResponseTest {

    @Test
    @DisplayName("CardTransactionResponse nesnesi başarıyla oluşturulmalı")
    void shouldCreateCardTransactionResponse() {
        // Given
        CardTransactionResponse response = new CardTransactionResponse();

        // Then
        assertNotNull(response);
    }
}
