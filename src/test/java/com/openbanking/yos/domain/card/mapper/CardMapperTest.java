package com.openbanking.yos.domain.card.mapper;

import com.openbanking.yos.domain.card.dto.request.CardRequest;
import com.openbanking.yos.domain.card.dto.response.CardResponse;
import com.openbanking.yos.domain.card.dto.response.CardTransactionResponse;
import com.openbanking.yos.domain.card.entity.CardEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CardMapperTest {

    @Test
    void shouldCreateMapper() {
        CardMapper mapper = new CardMapper() {};
        assertNotNull(mapper);
    }

    @Test
    void shouldCreateCardRequest() {
        CardRequest request = new CardRequest();
        assertNotNull(request);
    }

    @Test
    void shouldCreateCardResponse() {
        CardResponse response = new CardResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateCardTransactionResponse() {
        CardTransactionResponse response = new CardTransactionResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateCardEntity() {
        CardEntity entity = new CardEntity();
        assertNotNull(entity);
    }
}
