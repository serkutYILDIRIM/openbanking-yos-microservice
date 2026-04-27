package com.openbanking.yos.common.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class JwsUtilTest {

    @Test
    void shouldBeFinalClass() {
        assertTrue(Modifier.isFinal(JwsUtil.class.getModifiers()));
    }

    @Test
    void shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<JwsUtil> constructor = JwsUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    void shouldHaveExactlyOneConstructor() {
        assertEquals(1, JwsUtil.class.getDeclaredConstructors().length);
    }
}
