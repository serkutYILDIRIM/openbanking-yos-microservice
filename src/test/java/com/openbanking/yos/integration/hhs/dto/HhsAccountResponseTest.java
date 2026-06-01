package com.openbanking.yos.integration.hhs.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HhsAccountResponseTest {

    @Test
    @DisplayName("HhsAccountResponse nesnesi başarıyla oluşturulmalı")
    void shouldCreateHhsAccountResponse() {
        HhsAccountResponse response = new HhsAccountResponse();

        assertNotNull(response);
    }

    @Test
    @DisplayName("HhsAccountResponse farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        HhsAccountResponse response1 = new HhsAccountResponse();
        HhsAccountResponse response2 = new HhsAccountResponse();

        assertNotNull(response1);
        assertNotNull(response2);
        assertNotSame(response1, response2);
    }

    @Test
    @DisplayName("HhsAccountResponse doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.integration.hhs.dto", HhsAccountResponse.class.getPackageName());
    }

    @Test
    @DisplayName("HhsAccountResponse public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(HhsAccountResponse.class.getModifiers()));
    }

    @Test
    @DisplayName("HhsAccountResponse varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(HhsAccountResponse.class.getDeclaredConstructor());
    }
}

