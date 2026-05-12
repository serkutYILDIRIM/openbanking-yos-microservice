package com.openbanking.yos.domain.card.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CardEntityTest {

    @Test
    @DisplayName("CardEntity builder ve getter metodları düzgün çalışmalı")
    void shouldWorkWithBuilderAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        CardEntity card = CardEntity.builder()
                .id(1L)
                .rizaNo("riza-123")
                .kartRef("kart-456")
                .kartNo("123456******1234")
                .asilKartNo("123456******0000")
                .kartTipi("K")
                .altKartTipi("B")
                .kartFormu("F")
                .kartTuru("T")
                .kartStatu("A")
                .kartSahibi("John Doe")
                .kartTicariUnvan("Doe Corp")
                .kartUrunAdi("Gold Card")
                .kartRumuz("My Gold")
                .kartSema("V")
                .hhsKod("HHS01")
                .yosKod("YOS01")
                .olusZmn(now)
                .gnclZmn(now)
            
                .build();

        assertNotNull(card);
        assertEquals(1L, card.getId());
        assertEquals("riza-123", card.getRizaNo());
        assertEquals("kart-456", card.getKartRef());
        assertEquals("123456******1234", card.getKartNo());
        assertEquals("123456******0000", card.getAsilKartNo());
        assertEquals("K", card.getKartTipi());
        assertEquals("B", card.getAltKartTipi());
        assertEquals("F", card.getKartFormu());
        assertEquals("T", card.getKartTuru());
        assertEquals("A", card.getKartStatu());
        assertEquals("John Doe", card.getKartSahibi());
        assertEquals("Doe Corp", card.getKartTicariUnvan());
        assertEquals("Gold Card", card.getKartUrunAdi());
        assertEquals("My Gold", card.getKartRumuz());
        assertEquals("V", card.getKartSema());
        assertEquals("HHS01", card.getHhsKod());
        assertEquals("YOS01", card.getYosKod());
        assertEquals(now, card.getOlusZmn());
        assertEquals(now, card.getGnclZmn());
    }

    @Test
    @DisplayName("CardEntity setter metodları düzgün çalışmalı")
    void shouldWorkWithSetters() {
        CardEntity card = new CardEntity();
        card.setId(10L);
        card.setRizaNo("riza-set");
        card.setKartRef("kart-set");

        assertEquals(10L, card.getId());
        assertEquals("riza-set", card.getRizaNo());
        assertEquals("kart-set", card.getKartRef());
    }

    @Test
    @DisplayName("CardEntity equals ve hashCode metodları düzgün çalışmalı")
    void shouldWorkWithEqualsAndHashCode() {
        CardEntity card1 = CardEntity.builder().id(1L).kartRef("ref1").build();
        CardEntity card2 = CardEntity.builder().id(1L).kartRef("ref1").build();
        CardEntity card3 = CardEntity.builder().id(2L).kartRef("ref2").build();

        assertEquals(card1, card2);
        assertNotEquals(card1, card3);
        assertEquals(card1.hashCode(), card2.hashCode());
        assertNotEquals(card1.hashCode(), card3.hashCode());
    }
}
