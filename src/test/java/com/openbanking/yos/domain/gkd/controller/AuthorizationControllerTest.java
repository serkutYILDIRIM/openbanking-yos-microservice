package com.openbanking.yos.domain.gkd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationControllerTest {

    @InjectMocks
    private AuthorizationController authorizationController;

    @Test
    void shouldCreateControllerSuccessfully() {
        assertNotNull(authorizationController);
    }

    @Test
    void shouldHaveRestControllerAnnotation() {
        assertTrue(AuthorizationController.class.isAnnotationPresent(RestController.class));
    }

    @Test
    void shouldHaveCorrectRequestMappingPath() {
        RequestMapping requestMapping = AuthorizationController.class.getAnnotation(RequestMapping.class);
        assertNotNull(requestMapping);
        assertEquals("/gkd", requestMapping.value()[0]);
    }
}
