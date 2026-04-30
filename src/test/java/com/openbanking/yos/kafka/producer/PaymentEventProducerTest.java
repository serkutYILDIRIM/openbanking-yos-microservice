package com.openbanking.yos.kafka.producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEventProducerTest {

    @Test
    @DisplayName("PaymentEventProducer nesnesi başarıyla oluşturulmalı")
    void shouldCreatePaymentEventProducer() {
        PaymentEventProducer producer = new PaymentEventProducer();

        assertNotNull(producer);
    }

    @Test
    @DisplayName("PaymentEventProducer @Component annotation'a sahip olmalı")
    void shouldHaveComponentAnnotation() {
        assertTrue(PaymentEventProducer.class.isAnnotationPresent(Component.class));
    }

    @Test
    @DisplayName("PaymentEventProducer farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        PaymentEventProducer producer1 = new PaymentEventProducer();
        PaymentEventProducer producer2 = new PaymentEventProducer();

        assertNotNull(producer1);
        assertNotNull(producer2);
        assertNotSame(producer1, producer2);
    }
}

