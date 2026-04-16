package com.openbanking.yos.domain.card.dto.response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class CardResponseTest {

    @Test
    @DisplayName("CardResponse builder ve getter metodları düzgün çalışmalı")
    void shouldWorkWithBuilderAndGetters() {
        // Given
        CardResponse.EkstreTur ekstreTur = CardResponse.EkstreTur.builder()
                .ekstreStatu("A")
                .paraBirimi("TRY")
                .build();

        CardResponse cardResponse = CardResponse.builder()
                .rizaNo("riza-123")
                .kartRef("kart-456")
                .kartNo("123456******1234")
                .asilKartNo("123456******1111")
                .kartTipi("K")
                .altKartTipi("B")
                .kartFormu("F")
                .kartTuru("T")
                .kartStatu("A")
                .kartSahibi("John Doe")
                .kartTicariUnvan("Doe Corp")
                .kartUrunAdi("Gold Card")
                .ekstreTurleri(Collections.singletonList(ekstreTur))
                .kartRumuz("My Card")
                .kartSema("V")
                .build();

        // Then
        assertNotNull(cardResponse);
        assertEquals("riza-123", cardResponse.getRizaNo());
        assertEquals("kart-456", cardResponse.getKartRef());
        assertEquals("123456******1234", cardResponse.getKartNo());
        assertEquals("123456******1111", cardResponse.getAsilKartNo());
        assertEquals("K", cardResponse.getKartTipi());
        assertEquals("B", cardResponse.getAltKartTipi());
        assertEquals("F", cardResponse.getKartFormu());
        assertEquals("T", cardResponse.getKartTuru());
        assertEquals("A", cardResponse.getKartStatu());
        assertEquals("John Doe", cardResponse.getKartSahibi());
        assertEquals("Doe Corp", cardResponse.getKartTicariUnvan());
        assertEquals("Gold Card", cardResponse.getKartUrunAdi());
        assertNotNull(cardResponse.getEkstreTurleri());
        assertEquals(1, cardResponse.getEkstreTurleri().size());
        assertEquals("A", cardResponse.getEkstreTurleri().get(0).getEkstreStatu());
        assertEquals("TRY", cardResponse.getEkstreTurleri().get(0).getParaBirimi());
        assertEquals("My Card", cardResponse.getKartRumuz());
        assertEquals("V", cardResponse.getKartSema());
    }

    @Test
    @DisplayName("CardResponse setter metodları düzgün çalışmalı")
    void shouldWorkWithSetters() {
        // Given
        CardResponse cardResponse = new CardResponse();

        // When
        cardResponse.setRizaNo("riza-set");
        cardResponse.setKartRef("kart-set");
        cardResponse.setKartNo("123456******0000");

        // Then
        assertEquals("riza-set", cardResponse.getRizaNo());
        assertEquals("kart-set", cardResponse.getKartRef());
        assertEquals("123456******0000", cardResponse.getKartNo());
    }

    @Test
    @DisplayName("CardResponse equals ve hashCode metodları düzgün çalışmalı")
    void shouldWorkWithEqualsAndHashCode() {
        // Given
        CardResponse response1 = CardResponse.builder().rizaNo("123").build();
        CardResponse response2 = CardResponse.builder().rizaNo("123").build();
        CardResponse response3 = CardResponse.builder().rizaNo("456").build();

        // Then
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    @DisplayName("EkstreTur getter ve setter metodları düzgün çalışmalı")
    void shouldWorkWithEkstreTurGettersAndSetters() {
        // Given
        CardResponse.EkstreTur ekstreTur = new CardResponse.EkstreTur();

        // When
        ekstreTur.setEkstreStatu("I");
        ekstreTur.setParaBirimi("USD");

        // Then
        assertEquals("I", ekstreTur.getEkstreStatu());
        assertEquals("USD", ekstreTur.getParaBirimi());
    }
}
