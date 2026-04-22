package com.openbanking.yos.common.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void shouldBeFinalClass() {
        assertTrue(Modifier.isFinal(DateUtil.class.getModifiers()));
    }

    @Test
    void shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<DateUtil> constructor = DateUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    void shouldHaveExactlyOneConstructor() {
        assertEquals(1, DateUtil.class.getDeclaredConstructors().length);
    }
}
