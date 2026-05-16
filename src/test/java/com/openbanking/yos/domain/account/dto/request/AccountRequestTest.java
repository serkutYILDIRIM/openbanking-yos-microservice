package com.openbanking.yos.domain.account.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRequestTest {
    @Test
    @DisplayName("AccountRequest nesnesi başarıyla oluşturulmalı")
    void shouldCreateAccountRequest() {
        AccountRequest request = new AccountRequest();

        assertNotNull(request);
    }

    @Test
    @DisplayName("AccountRequest farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        AccountRequest request1 = new AccountRequest();
        AccountRequest request2 = new AccountRequest();

        assertNotNull(request1);
        assertNotNull(request2);

        assertNotSame(request1, request2);
    }

    @Test
    @DisplayName("AccountRequest doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.account.dto.request", AccountRequest.class.getPackageName());
    }

    @Test
    @DisplayName("AccountRequest public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(AccountRequest.class.getModifiers()));
    }

    @Test
    @DisplayName("AccountRequest varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(AccountRequest.class.getDeclaredConstructor());
    }
}
