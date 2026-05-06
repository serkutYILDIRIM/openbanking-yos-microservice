package com.openbanking.yos.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class KafkaConfigTest {

    @Test
    @DisplayName("KafkaConfig nesnesi başarıyla oluşturulmalı")
    void shouldCreateKafkaConfig() {
        KafkaConfig config = new KafkaConfig();

        assertNotNull(config);
    }

    @Test
    @DisplayName("KafkaConfig @Configuration annotation'a sahip olmalı")
    void shouldHaveConfigurationAnnotation() {
        assertTrue(KafkaConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    @DisplayName("KafkaConfig farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        KafkaConfig config1 = new KafkaConfig();
        KafkaConfig config2 = new KafkaConfig();

        assertNotNull(config1);
        assertNotNull(config2);
        assertNotSame(config1, config2);
    }
}
