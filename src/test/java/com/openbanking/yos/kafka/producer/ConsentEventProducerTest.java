package com.openbanking.yos.kafka.producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

class ConsentEventProducerTest {

    @Test
    @DisplayName("ConsentEventProducer nesnesi başarıyla oluşturulmalı")
    void shouldCreateConsentEventProducer() {
        ConsentEventProducer producer = new ConsentEventProducer();

        assertNotNull(producer);
    }

    @Test
    @DisplayName("ConsentEventProducer @Component annotation'a sahip olmalı")
    void shouldHaveComponentAnnotation() {
        assertTrue(ConsentEventProducer.class.isAnnotationPresent(Component.class));
    }

    @Test
    @DisplayName("ConsentEventProducer farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        ConsentEventProducer producer1 = new ConsentEventProducer();
        ConsentEventProducer producer2 = new ConsentEventProducer();

        assertNotNull(producer1);
        assertNotNull(producer2);
        assertNotSame(producer1, producer2);
    }
}
