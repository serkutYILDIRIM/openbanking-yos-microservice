package com.openbanking.yos.kafka.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEventTest {

    @Test
    @DisplayName("PaymentEvent nesnesi başarıyla oluşturulmalı")
    void shouldCreatePaymentEvent() {
        PaymentEvent event = new PaymentEvent();

        assertNotNull(event);
    }

    @Test
    @DisplayName("PaymentEvent farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        PaymentEvent event1 = new PaymentEvent();
        PaymentEvent event2 = new PaymentEvent();

        assertNotNull(event1);
        assertNotNull(event2);
        assertNotSame(event1, event2);
    }

    @Test
    @DisplayName("PaymentEvent doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.kafka.event", PaymentEvent.class.getPackageName());
    }

    @Test
    @DisplayName("PaymentEvent public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(PaymentEvent.class.getModifiers()));
    }

    @Test
    @DisplayName("PaymentEvent varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(PaymentEvent.class.getDeclaredConstructor());
    }
}
