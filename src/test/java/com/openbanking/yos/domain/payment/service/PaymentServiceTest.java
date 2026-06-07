package com.openbanking.yos.domain.payment.service;

import com.openbanking.yos.domain.payment.dto.request.PaymentRequest;
import com.openbanking.yos.domain.payment.dto.response.PaymentResponse;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    void shouldBeInterface() {
        assertTrue(PaymentService.class.isInterface());
    }

    @Test
    void shouldHaveCreatePaymentMethod() throws NoSuchMethodException {
        Method method = PaymentService.class.getDeclaredMethod(
                "createPayment", PaymentRequest.class, String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(PaymentResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveGetPaymentMethod() throws NoSuchMethodException {
        Method method = PaymentService.class.getDeclaredMethod(
                "getPayment", String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(PaymentResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveExactlyTwoDeclaredMethods() {
        Method[] methods = PaymentService.class.getDeclaredMethods();

        assertEquals(2, methods.length);
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.payment.service", PaymentService.class.getPackageName());
    }

    @Test
    void shouldBeImplementedByPaymentServiceImpl() {
        assertTrue(PaymentService.class.isAssignableFrom(PaymentServiceImpl.class));
    }
}
