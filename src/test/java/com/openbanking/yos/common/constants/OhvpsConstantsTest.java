package com.openbanking.yos.common.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class OhvpsConstantsTest {

    @Test
    void shouldBeFinalClass() {
        assertTrue(Modifier.isFinal(OhvpsConstants.class.getModifiers()));
    }

    @Test
    void shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<OhvpsConstants> constructor = OhvpsConstants.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    void shouldHaveExactlyOneConstructor() {
        assertEquals(1, OhvpsConstants.class.getDeclaredConstructors().length);
    }

    @Test
    void shouldAllowReflectiveInstantiation() throws Exception {
        Constructor<OhvpsConstants> constructor = OhvpsConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }
}
