package com.openbanking.yos.domain.gkd.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceImplTest {

    @InjectMocks
    private AuthorizationServiceImpl authorizationService;

    @Test
    void shouldCreateServiceSuccessfully() {
        assertNotNull(authorizationService);
    }
}
