package com.openbanking.yos.domain.gkd.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenRequestTest {

    @Test
    @DisplayName("AccessTokenRequest nesnesi başarıyla oluşturulmalı")
    void shouldCreateAccessTokenRequest() {
        AccessTokenRequest request = new AccessTokenRequest();

        assertNotNull(request);
    }

    @Test
    @DisplayName("AccessTokenRequest farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        AccessTokenRequest request1 = new AccessTokenRequest();
        AccessTokenRequest request2 = new AccessTokenRequest();

        assertNotNull(request1);
        assertNotNull(request2);

        assertNotSame(request1, request2);
    }

    @Test
    @DisplayName("AccessTokenRequest doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.gkd.dto.request", AccessTokenRequest.class.getPackageName());
    }

    @Test
    @DisplayName("AccessTokenRequest public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(AccessTokenRequest.class.getModifiers()));
    }

    @Test
    @DisplayName("AccessTokenRequest varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(AccessTokenRequest.class.getDeclaredConstructor());
    }
}
