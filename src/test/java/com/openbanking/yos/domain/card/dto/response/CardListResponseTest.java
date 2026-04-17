package com.openbanking.yos.domain.card.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardListResponseTest {

    @Test
    @DisplayName("CardListResponse builder ve getter metodları düzgün çalışmalı")
    void shouldWorkWithBuilderAndGetters() {
        // Given
        CardResponse cardResponse = CardResponse.builder()
                .kartRef("kart-123")
                .build();
        List<CardResponse> cardList = Collections.singletonList(cardResponse);

        CardListResponse cardListResponse = CardListResponse.builder()
                .kartlar(cardList)
                .build();

        // Then
        assertNotNull(cardListResponse);
        assertNotNull(cardListResponse.getKartlar());
        assertEquals(1, cardListResponse.getKartlar().size());
        assertEquals("kart-123", cardListResponse.getKartlar().get(0).getKartRef());
    }

    @Test
    @DisplayName("CardListResponse setter metodları düzgün çalışmalı")
    void shouldWorkWithSetters() {
        // Given
        CardListResponse cardListResponse = new CardListResponse();
        CardResponse cardResponse = CardResponse.builder()
                .kartRef("kart-456")
                .build();
        List<CardResponse> cardList = Collections.singletonList(cardResponse);

        // When
        cardListResponse.setKartlar(cardList);

        // Then
        assertEquals(cardList, cardListResponse.getKartlar());
        assertEquals(1, cardListResponse.getKartlar().size());
        assertEquals("kart-456", cardListResponse.getKartlar().get(0).getKartRef());
    }

    @Test
    @DisplayName("CardListResponse equals ve hashCode metodları düzgün çalışmalı")
    void shouldWorkWithEqualsAndHashCode() {
        // Given
        CardResponse card1 = CardResponse.builder().kartRef("1").build();
        CardResponse card2 = CardResponse.builder().kartRef("2").build();

        CardListResponse response1 = CardListResponse.builder()
                .kartlar(Collections.singletonList(card1))
                .build();
        CardListResponse response2 = CardListResponse.builder()
                .kartlar(Collections.singletonList(card1))
                .build();
        CardListResponse response3 = CardListResponse.builder()
                .kartlar(Collections.singletonList(card2))
                .build();

        // Then
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    @DisplayName("CardListResponse toString metodu düzgün çalışmalı")
    void shouldWorkWithToString() {
        // Given
        CardListResponse cardListResponse = CardListResponse.builder()
                .kartlar(Collections.emptyList())
                .build();

        // Then
        assertNotNull(cardListResponse.toString());
        assertTrue(cardListResponse.toString().contains("kartlar"));
    }
}
