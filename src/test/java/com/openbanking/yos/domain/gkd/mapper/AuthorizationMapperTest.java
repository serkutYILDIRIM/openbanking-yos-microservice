package com.openbanking.yos.domain.gkd.mapper;

import com.openbanking.yos.domain.gkd.dto.request.AccessTokenRequest;
import com.openbanking.yos.domain.gkd.dto.response.AccessTokenResponse;
import com.openbanking.yos.domain.gkd.dto.response.AuthorizationCodeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthorizationMapperTest {

    @Test
    void shouldCreateMapper() {
        AuthorizationMapper mapper = new AuthorizationMapper() {};
        assertNotNull(mapper);
    }

    @Test
    void shouldCreateAccessTokenRequest() {
        AccessTokenRequest request = new AccessTokenRequest();
        assertNotNull(request);
    }

    @Test
    void shouldCreateAccessTokenResponse() {
        AccessTokenResponse response = new AccessTokenResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateAuthorizationCodeResponse() {
        AuthorizationCodeResponse response = new AuthorizationCodeResponse();
        assertNotNull(response);
    }
}
