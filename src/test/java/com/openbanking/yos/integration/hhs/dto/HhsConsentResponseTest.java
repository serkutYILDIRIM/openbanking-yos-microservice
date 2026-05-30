package com.openbanking.yos.integration.hhs.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HhsConsentResponseTest {

    @Test
    @DisplayName("HhsConsentResponse nesnesi başarıyla oluşturulmalı")
    void shouldCreateHhsConsentResponse() {
        HhsConsentResponse response = new HhsConsentResponse();

        assertNotNull(response);
    }

    @Test
    @DisplayName("HhsConsentResponse farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        HhsConsentResponse response1 = new HhsConsentResponse();
        HhsConsentResponse response2 = new HhsConsentResponse();

        assertNotNull(response1);
        assertNotNull(response2);
        assertNotSame(response1, response2);
    }

    @Test
    @DisplayName("HhsConsentResponse doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.integration.hhs.dto", HhsConsentResponse.class.getPackageName());
    }

    @Test
    @DisplayName("HhsConsentResponse public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(HhsConsentResponse.class.getModifiers()));
    }

    @Test
    @DisplayName("HhsConsentResponse varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(HhsConsentResponse.class.getDeclaredConstructor());
    }
}
