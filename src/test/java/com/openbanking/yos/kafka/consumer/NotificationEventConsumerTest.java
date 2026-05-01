package com.openbanking.yos.kafka.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

class NotificationEventConsumerTest {

    @Test
    @DisplayName("NotificationEventConsumer nesnesi başarıyla oluşturulmalı")
    void shouldCreateNotificationEventConsumer() {
        NotificationEventConsumer consumer = new NotificationEventConsumer();

        assertNotNull(consumer);
    }

    @Test
    @DisplayName("NotificationEventConsumer @Component annotation'a sahip olmalı")
    void shouldHaveComponentAnnotation() {
        assertTrue(NotificationEventConsumer.class.isAnnotationPresent(Component.class));
    }

    @Test
    @DisplayName("NotificationEventConsumer farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        NotificationEventConsumer consumer1 = new NotificationEventConsumer();
        NotificationEventConsumer consumer2 = new NotificationEventConsumer();

        assertNotNull(consumer1);
        assertNotNull(consumer2);
        assertNotSame(consumer1, consumer2);
    }
}
