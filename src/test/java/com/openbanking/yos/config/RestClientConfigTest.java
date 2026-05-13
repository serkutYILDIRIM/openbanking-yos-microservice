package com.openbanking.yos.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class RestClientConfigTest {
    @Test
    @DisplayName("RestClientConfig nesnesi başarıyla oluşturulmalı")
    void shouldCreateRestClientConfig() {
        RestClientConfig config = new RestClientConfig();

        assertNotNull(config);
    }
    @Test
    @DisplayName("RestClientConfig @Configuration annotation'a sahip olmalı")
    void shouldHaveConfigurationAnnotation() {
        assertTrue(RestClientConfig.class.isAnnotationPresent(Configuration.class));
    }
    @Test
    @DisplayName("RestClientConfig farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        RestClientConfig config1 = new RestClientConfig();
        RestClientConfig config2 = new RestClientConfig();

        assertNotNull(config1);
        assertNotNull(config2);
        assertNotSame(config1, config2);
    }
}
