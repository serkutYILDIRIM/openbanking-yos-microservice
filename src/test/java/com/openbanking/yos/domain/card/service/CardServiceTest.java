package com.openbanking.yos.domain.card.service;

import com.openbanking.yos.domain.card.dto.response.CardListResponse;
import com.openbanking.yos.domain.card.dto.response.CardResponse;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CardServiceTest {

    @Test
    void shouldBeInterface() {
        assertTrue(CardService.class.isInterface());
    }

    @Test
    void shouldHaveGetCardsMethod() throws NoSuchMethodException {
        Method method = CardService.class.getDeclaredMethod(
                "getCards", String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(CardListResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveGetCardMethod() throws NoSuchMethodException {
        Method method = CardService.class.getDeclaredMethod(
                "getCard", String.class, String.class, String.class, String.class);

        assertNotNull(method);
        assertEquals(CardResponse.class, method.getReturnType());
    }

    @Test
    void shouldHaveExactlyTwoDeclaredMethods() {

        Method[] methods = CardService.class.getDeclaredMethods();

        assertEquals(2, methods.length);

    }

    @Test
    void shouldBeInCorrectPackage() {

        assertEquals("com.openbanking.yos.domain.card.service", CardService.class.getPackageName());

    }

    @Test
    void shouldBeImplementedByCardServiceImpl() {

        assertTrue(CardService.class.isAssignableFrom(CardServiceImpl.class));

    }
}
