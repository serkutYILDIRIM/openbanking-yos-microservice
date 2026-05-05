package com.openbanking.yos.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    @DisplayName("SecurityConfig nesnesi başarıyla oluşturulmalı")
    void shouldCreateSecurityConfig() {
        SecurityConfig config = new SecurityConfig();

        assertNotNull(config);
    }

    @Test
    @DisplayName("SecurityConfig @Configuration annotation'a sahip olmalı")
    void shouldHaveConfigurationAnnotation() {
        assertTrue(SecurityConfig.class.isAnnotationPresent(Configuration.class));
    }

    @Test
    @DisplayName("SecurityConfig farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        SecurityConfig config1 = new SecurityConfig();
        SecurityConfig config2 = new SecurityConfig();

        assertNotNull(config1);
        assertNotNull(config2);
        assertNotSame(config1, config2);
    }
}

