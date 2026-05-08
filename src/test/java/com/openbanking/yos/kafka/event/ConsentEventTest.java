package com.openbanking.yos.kafka.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsentEventTest {

    @Test
    @DisplayName("ConsentEvent nesnesi başarıyla oluşturulmalı")
    void shouldCreateConsentEvent() {
        ConsentEvent event = new ConsentEvent();

        assertNotNull(event);
    }

    @Test
    @DisplayName("ConsentEvent farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        ConsentEvent event1 = new ConsentEvent();
        ConsentEvent event2 = new ConsentEvent();

        assertNotNull(event1);
        assertNotNull(event2);
        assertNotSame(event1, event2);
    }

    @Test
    @DisplayName("ConsentEvent doğru pakette tanımlanmış olmalı")
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.kafka.event", ConsentEvent.class.getPackageName());
    }

    @Test
    @DisplayName("ConsentEvent public sınıf olmalı")
    void shouldBePublicClass() {
        assertTrue(java.lang.reflect.Modifier.isPublic(ConsentEvent.class.getModifiers()));
    }

    @Test
    @DisplayName("ConsentEvent varsayılan constructor'a sahip olmalı")
    void shouldHaveDefaultConstructor() throws NoSuchMethodException {
        assertNotNull(ConsentEvent.class.getDeclaredConstructor());
    }
}
