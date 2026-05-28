package com.openbanking.yos.domain.gkd.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenResponseTest {

    @Test
    @DisplayName("AccessTokenResponse nesnesi başarıyla oluşturulmalı")
    void shouldCreateAccessTokenResponse() {
        AccessTokenResponse response = new AccessTokenResponse();

        assertNotNull(response);
    }

    @Test
    @DisplayName("AccessTokenResponse farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        AccessTokenResponse response1 = new AccessTokenResponse();
        AccessTokenResponse response2 = new AccessTokenResponse();

        assertNotNull(response1);
        assertNotNull(response2);
        assertNotSame(response1, response2);
    }

    @Test
    @DisplayName("AccessTokenResponse doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.gkd.dto.response", AccessTokenResponse.class.getPackageName());
    }

    @Test
    @DisplayName("AccessTokenResponse public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(AccessTokenResponse.class.getModifiers()));
    }

    @Test
    @DisplayName("AccessTokenResponse varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(AccessTokenResponse.class.getDeclaredConstructor());
    }
}
