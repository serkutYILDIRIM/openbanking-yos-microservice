package com.openbanking.yos.domain.consent.repository;

import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ConsentRepositoryTest {

    @Test
    void shouldBeInterfaceExtendingJpaRepository() {
        
        assertTrue(ConsentRepository.class.isInterface());
        assertTrue(JpaRepository.class.isAssignableFrom(ConsentRepository.class));
        
    }

    @Test
    void shouldHaveFindByRizaNoMethod() throws NoSuchMethodException {
        
        Method method = ConsentRepository.class.getDeclaredMethod("findByRizaNo", String.class);

        assertNotNull(method);
        assertEquals(Optional.class, method.getReturnType());
        
    }

    @Test
    void shouldHaveCorrectGenericTypes() {
        Class<?>[] interfaces = ConsentRepository.class.getInterfaces();

        assertEquals(1, interfaces.length);
        assertEquals(JpaRepository.class, interfaces[0]);
    }

    @Test
    void shouldHaveEntityTypeAsConsentEntity() {
        java.lang.reflect.Type[] genericInterfaces = ConsentRepository.class.getGenericInterfaces();

        assertNotNull(genericInterfaces);
        assertEquals(1, genericInterfaces.length);
        String typeName = genericInterfaces[0].getTypeName();
        assertTrue(typeName.contains(ConsentEntity.class.getName()));
        assertTrue(typeName.contains(Long.class.getName()));
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.consent.repository", ConsentRepository.class.getPackageName());
    }

    @Test
    void shouldHaveExactlyOneDeclaredMethod() {
        Method[] methods = ConsentRepository.class.getDeclaredMethods();

        assertEquals(1, methods.length);
    }
}
