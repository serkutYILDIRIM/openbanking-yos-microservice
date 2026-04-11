package com.openbanking.yos.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GkdTypeTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertNotNull(GkdType.valueOf("Y"));
        assertNotNull(GkdType.valueOf("A"));
    }

    @Test
    void shouldReturnCorrectNames() {
        assertEquals("Y", GkdType.Y.name());
        assertEquals("A", GkdType.A.name());
    }

    @Test
    void shouldHaveExpectedNumberOfValues() {
        assertEquals(2, GkdType.values().length);
    }
}
