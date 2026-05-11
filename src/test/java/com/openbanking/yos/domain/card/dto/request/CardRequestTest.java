package com.openbanking.yos.domain.card.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardRequestTest {
    @Test
    @DisplayName("CardRequest nesnesi başarıyla oluşturulmalı")
    void shouldCreateCardRequest() {
        CardRequest request = new CardRequest();

        assertNotNull(request);
    }
    @Test
    @DisplayName("CardRequest farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        CardRequest request1 = new CardRequest();
        CardRequest request2 = new CardRequest();

        assertNotNull(request1);
        assertNotNull(request2);
        assertNotSame(request1, request2);
    }
    @Test
    @DisplayName("CardRequest doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.card.dto.request", CardRequest.class.getPackageName());
    }
    @Test
    @DisplayName("CardRequest public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(CardRequest.class.getModifiers()));
    }
    @Test
    @DisplayName("CardRequest varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(CardRequest.class.getDeclaredConstructor());
    }
}
