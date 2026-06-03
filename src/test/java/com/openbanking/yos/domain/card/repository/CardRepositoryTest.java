package com.openbanking.yos.domain.card.repository;

import com.openbanking.yos.domain.card.entity.CardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CardRepositoryTest {

    @Test
    void shouldBeInterfaceExtendingJpaRepository() {
        assertTrue(CardRepository.class.isInterface());
        assertTrue(JpaRepository.class.isAssignableFrom(CardRepository.class));
    }

    @Test
    void shouldHaveFindByRizaNoMethod() throws NoSuchMethodException {
        Method method = CardRepository.class.getDeclaredMethod("findByRizaNo", String.class);

        assertNotNull(method);
        assertEquals(List.class, method.getReturnType());
    }

    @Test
    void shouldHaveFindByKartRefMethod() throws NoSuchMethodException {
        Method method = CardRepository.class.getDeclaredMethod("findByKartRef", String.class);

        assertNotNull(method);
        assertEquals(Optional.class, method.getReturnType());
    }

    @Test
    void shouldHaveCorrectGenericTypes() {
        Class<?>[] interfaces = CardRepository.class.getInterfaces();

        assertEquals(1, interfaces.length);
        assertEquals(JpaRepository.class, interfaces[0]);
    }

    @Test
    void shouldHaveEntityTypeAsCardEntity() {
        java.lang.reflect.Type[] genericInterfaces = CardRepository.class.getGenericInterfaces();

        assertNotNull(genericInterfaces);
        assertEquals(1, genericInterfaces.length);
        String typeName = genericInterfaces[0].getTypeName();
        assertTrue(typeName.contains(CardEntity.class.getName()));
        assertTrue(typeName.contains(Long.class.getName()));
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.card.repository", CardRepository.class.getPackageName());
    }

    @Test
    void shouldHaveExactlyTwoDeclaredMethods() {
        Method[] methods = CardRepository.class.getDeclaredMethods();

        assertEquals(2, methods.length);
    }
}
