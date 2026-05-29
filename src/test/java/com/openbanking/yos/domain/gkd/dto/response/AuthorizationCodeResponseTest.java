package com.openbanking.yos.domain.gkd.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationCodeResponseTest {

    @Test
    @DisplayName("AuthorizationCodeResponse nesnesi başarıyla oluşturulmalı")
    void shouldCreateAuthorizationCodeResponse() {
        AuthorizationCodeResponse response = new AuthorizationCodeResponse();

        assertNotNull(response);
    }

    @Test
    @DisplayName("AuthorizationCodeResponse farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        AuthorizationCodeResponse response1 = new AuthorizationCodeResponse();
        AuthorizationCodeResponse response2 = new AuthorizationCodeResponse();

        assertNotNull(response1);
        assertNotNull(response2);
        assertNotSame(response1, response2);
    }

    @Test
    @DisplayName("AuthorizationCodeResponse doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.gkd.dto.response", AuthorizationCodeResponse.class.getPackageName());
    }

    @Test
    @DisplayName("AuthorizationCodeResponse public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(AuthorizationCodeResponse.class.getModifiers()));
    }

    @Test
    @DisplayName("AuthorizationCodeResponse varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(AuthorizationCodeResponse.class.getDeclaredConstructor());
    }
}
