package com.openbanking.yos.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentStatusTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertNotNull(PaymentStatus.valueOf("G"));
        assertNotNull(PaymentStatus.valueOf("K"));
    }

    @Test
    void shouldReturnCorrectNames() {
        assertEquals("G", PaymentStatus.G.name());
        assertEquals("K", PaymentStatus.K.name());
    }

    @Test
    void shouldHaveExpectedNumberOfValues() {
        assertEquals(2, PaymentStatus.values().length);
    }
}
