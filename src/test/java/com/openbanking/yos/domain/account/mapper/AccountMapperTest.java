package com.openbanking.yos.domain.account.mapper;

import com.openbanking.yos.domain.account.dto.request.AccountRequest;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;
import com.openbanking.yos.domain.account.entity.AccountEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    @Test
    void shouldCreateMapper() {
        AccountMapper mapper = new AccountMapper() {};
        assertNotNull(mapper);
    }

    @Test
    void shouldCreateAccountRequest() {
        AccountRequest request = new AccountRequest();
        assertNotNull(request);
    }

    @Test
    void shouldCreateAccountResponse() {
        AccountResponse response = new AccountResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateAccountEntity() {
        AccountEntity entity = new AccountEntity();
        assertNotNull(entity);
    }
}
