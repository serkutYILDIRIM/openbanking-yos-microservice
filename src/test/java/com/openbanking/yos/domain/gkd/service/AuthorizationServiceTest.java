package com.openbanking.yos.domain.gkd.service;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationServiceTest {

    @Test
    void shouldBeInterface() {
        assertTrue(AuthorizationService.class.isInterface());
    }

    @Test
    void shouldHaveNoDeclaredMethods() {
        Method[] methods = AuthorizationService.class.getDeclaredMethods();

        assertEquals(0, methods.length);
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.gkd.service", AuthorizationService.class.getPackageName());
    }

    @Test
    void shouldBeImplementedByAuthorizationServiceImpl() {
        assertTrue(AuthorizationService.class.isAssignableFrom(AuthorizationServiceImpl.class));
    }
}
