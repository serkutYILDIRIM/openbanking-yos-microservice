package com.openbanking.yos.domain.account.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountResponseTest {

    @Test
    void shouldCreateAccountResponseWithBuilder() {
        AccountResponse.HesapTemel hspTml = AccountResponse.HesapTemel.builder()
                .hspRef("ref-1")
                .hspNo("TR123")
                .hspShb("John Doe")
                .hspDrm("AKTIF")
                .build();

        AccountResponse response = AccountResponse.builder()
                .rizaNo("riza-1")
                .hspTml(hspTml)
                .build();

        assertNotNull(response);
        assertEquals("riza-1", response.getRizaNo());
        assertNotNull(response.getHspTml());
        assertEquals("ref-1", response.getHspTml().getHspRef());
        assertEquals("AKTIF", response.getHspTml().getHspDrm());
    }

    @Test
    void shouldTestNestedClassesNoArgsConstructor() {
        AccountResponse.HesapTemel tml = new AccountResponse.HesapTemel();
        tml.setHspRef("ref-2");
        assertEquals("ref-2", tml.getHspRef());

        AccountResponse.HesapDetay dty = new AccountResponse.HesapDetay();
        LocalDateTime now = LocalDateTime.now();
        dty.setHspAclsTrh(now);
        assertEquals(now, dty.getHspAclsTrh());
    }
}
