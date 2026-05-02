package com.openbanking.yos.integration.hhs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

class HhsApiClientTest {

    @Test
    @DisplayName("HhsApiClient nesnesi başarıyla oluşturulmalı")
    void shouldCreateHhsApiClient() {
        HhsApiClient client = new HhsApiClient();

        assertNotNull(client);
    }

    @Test
    @DisplayName("HhsApiClient @Component annotation'a sahip olmalı")
    void shouldHaveComponentAnnotation() {
        assertTrue(HhsApiClient.class.isAnnotationPresent(Component.class));
    }

    @Test
    @DisplayName("HhsApiClient farklı nesneler birbirinden bağımsız olmalı")
    void shouldCreateIndependentInstances() {
        HhsApiClient client1 = new HhsApiClient();
        HhsApiClient client2 = new HhsApiClient();

        assertNotNull(client1);
        assertNotNull(client2);
        assertNotSame(client1, client2);
    }
}

