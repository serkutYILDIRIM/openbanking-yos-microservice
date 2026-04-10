package com.openbanking.yos.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountStatusTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertNotNull(AccountStatus.valueOf("AKTIF"));
        assertNotNull(AccountStatus.valueOf("PASIF"));
        assertNotNull(AccountStatus.valueOf("KAPALI"));
    }

    @Test
    void shouldReturnCorrectNames() {
        assertEquals("AKTIF", AccountStatus.AKTIF.name());
        assertEquals("PASIF", AccountStatus.PASIF.name());
        assertEquals("KAPALI", AccountStatus.KAPALI.name());
    }

    @Test
    void shouldHaveExpectedNumberOfValues() {
        assertEquals(3, AccountStatus.values().length);
    }
}
