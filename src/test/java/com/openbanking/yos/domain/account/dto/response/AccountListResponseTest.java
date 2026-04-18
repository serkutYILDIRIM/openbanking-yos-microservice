package com.openbanking.yos.domain.account.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountListResponseTest {

    @Test
    @DisplayName("AccountListResponse builder ile başarıyla oluşturulmalı")
    void shouldCreateAccountListResponseWithBuilder() {
        AccountResponse accountResponse = AccountResponse.builder()
                .rizaNo("riza-123")
                .build();

        AccountListResponse response = AccountListResponse.builder()
                .hesaplar(List.of(accountResponse))
                .build();

        assertNotNull(response);
        assertEquals(1, response.getHesaplar().size());
        assertEquals("riza-123", response.getHesaplar().get(0).getRizaNo());
    }

    @Test
    @DisplayName("AccountListResponse no-args constructor ve setter'lar çalışmalı")
    void shouldCreateAccountListResponseWithSetters() {
        AccountListResponse response = new AccountListResponse();
        List<AccountResponse> accounts = Collections.singletonList(new AccountResponse());

        response.setHesaplar(accounts);

        assertNotNull(response.getHesaplar());
        assertEquals(1, response.getHesaplar().size());
    }
}
