package com.openbanking.yos.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

    @Test
    @DisplayName("OpenApiConfig nesnesi başarıyla oluşturulmalı")
    
    void shouldCreateOpenApiConfig() {
        OpenApiConfig config = new OpenApiConfig();

        assertNotNull(config);
    }

    @Test
    @DisplayName("OpenApiConfig @Configuration annotation'a sahip olmalı")
    void shouldHaveConfigurationAnnotation() {
        assertTrue(OpenApiConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    @DisplayName("OpenApiConfig farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        OpenApiConfig config1 = new OpenApiConfig();
        OpenApiConfig config2 = new OpenApiConfig();

        assertNotNull(config1);
        assertNotNull(config2);
        assertNotSame(config1, config2);
    }
}
