package com.openbanking.yos.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsentStatusTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertNotNull(ConsentStatus.valueOf("B"));
        assertNotNull(ConsentStatus.valueOf("Y"));
        assertNotNull(ConsentStatus.valueOf("K"));
        assertNotNull(ConsentStatus.valueOf("E"));
        assertNotNull(ConsentStatus.valueOf("S"));
        assertNotNull(ConsentStatus.valueOf("I"));
    }

    @Test
    void shouldReturnCorrectNames() {
        assertEquals("B", ConsentStatus.B.name());
        assertEquals("Y", ConsentStatus.Y.name());
        assertEquals("K", ConsentStatus.K.name());
        assertEquals("E", ConsentStatus.E.name());
        assertEquals("S", ConsentStatus.S.name());
        assertEquals("I", ConsentStatus.I.name());
    }

    @Test
    void shouldHaveExpectedNumberOfValues() {
        assertEquals(6, ConsentStatus.values().length);
    }
}
