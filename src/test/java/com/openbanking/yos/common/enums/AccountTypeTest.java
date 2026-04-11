package com.openbanking.yos.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountTypeTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertNotNull(AccountType.valueOf("VADESIZ"));
        assertNotNull(AccountType.valueOf("KREDILI_MEVDUAT"));
        assertNotNull(AccountType.valueOf("POS"));
        assertNotNull(AccountType.valueOf("CEK"));
        assertNotNull(AccountType.valueOf("YATIRIM"));
    }

    @Test
    void shouldReturnCorrectNames() {
        assertEquals("VADESIZ", AccountType.VADESIZ.name());
        assertEquals("KREDILI_MEVDUAT", AccountType.KREDILI_MEVDUAT.name());
        assertEquals("POS", AccountType.POS.name());
        assertEquals("CEK", AccountType.CEK.name());
        assertEquals("YATIRIM", AccountType.YATIRIM.name());
    }

    @Test
    void shouldHaveExpectedNumberOfValues() {
        assertEquals(5, AccountType.values().length);
    }
}
