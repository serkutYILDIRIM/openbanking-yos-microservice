package com.openbanking.yos.domain.account.service;

import com.openbanking.yos.domain.account.dto.response.AccountListResponse;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void shouldBeInterface() {
        assertTrue(AccountService.class.isInterface());
    }

    @Test
    void shouldHaveGetAccountsMethod() throws NoSuchMethodException {
        Method method = AccountService.class.getDeclaredMethod(
                "getAccounts", String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(AccountListResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveGetAccountMethod() throws NoSuchMethodException {
        Method method = AccountService.class.getDeclaredMethod(
                "getAccount", String.class, String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(AccountResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveExactlyTwoDeclaredMethods() {
        Method[] methods = AccountService.class.getDeclaredMethods();

        assertEquals(2, methods.length);
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.account.service", AccountService.class.getPackageName());
    }

    @Test
    void shouldBeImplementedByAccountServiceImpl() {
        assertTrue(AccountService.class.isAssignableFrom(AccountServiceImpl.class));
    }
}
