package com.openbanking.yos.domain.consent.service;

import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ConsentServiceTest {

    @Test
    void shouldBeInterface() {
        assertTrue(ConsentService.class.isInterface());
    }

    @Test
    void shouldHaveCreateConsentMethod() throws NoSuchMethodException {
        Method method = ConsentService.class.getDeclaredMethod(
                "createConsent", ConsentRequest.class, String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(ConsentResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveGetConsentMethod() throws NoSuchMethodException {
        Method method = ConsentService.class.getDeclaredMethod(
                "getConsent", String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(ConsentResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveExactlyTwoDeclaredMethods() {

        Method[] methods = ConsentService.class.getDeclaredMethods();

        assertEquals(2, methods.length);

    }

    @Test
    void shouldBeInCorrectPackage() {

        assertEquals("com.openbanking.yos.domain.consent.service", ConsentService.class.getPackageName());

    }

    @Test
    void shouldBeImplementedByConsentServiceImpl() {

        assertTrue(ConsentService.class.isAssignableFrom(ConsentServiceImpl.class));

    }
}
