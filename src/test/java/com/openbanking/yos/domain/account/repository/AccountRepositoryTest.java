package com.openbanking.yos.domain.account.repository;

import com.openbanking.yos.domain.account.entity.AccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    @Test
    void shouldBeInterfaceExtendingJpaRepository() {
        assertTrue(AccountRepository.class.isInterface());
        assertTrue(JpaRepository.class.isAssignableFrom(AccountRepository.class));
    }

    @Test
    void shouldHaveFindByRizaNoMethod() throws NoSuchMethodException {
        Method method = AccountRepository.class.getDeclaredMethod("findByRizaNo", String.class);

        assertNotNull(method);
        assertEquals(List.class, method.getReturnType());
    }

    @Test
    void shouldHaveFindByHspRefMethod() throws NoSuchMethodException {
        Method method = AccountRepository.class.getDeclaredMethod("findByHspRef", String.class);

        assertNotNull(method);
        assertEquals(Optional.class, method.getReturnType());
    }

    @Test
    void shouldHaveCorrectGenericTypes() {
        Class<?>[] interfaces = AccountRepository.class.getInterfaces();

        assertEquals(1, interfaces.length);
        assertEquals(JpaRepository.class, interfaces[0]);
    }

    @Test
    void shouldHaveEntityTypeAsAccountEntity() {
        java.lang.reflect.Type[] genericInterfaces = AccountRepository.class.getGenericInterfaces();

        assertNotNull(genericInterfaces);
        assertEquals(1, genericInterfaces.length);
        String typeName = genericInterfaces[0].getTypeName();
        assertTrue(typeName.contains(AccountEntity.class.getName()));
        assertTrue(typeName.contains(Long.class.getName()));
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.account.repository", AccountRepository.class.getPackageName());
    }

    @Test
    void shouldHaveExactlyTwoDeclaredMethods() {
        Method[] methods = AccountRepository.class.getDeclaredMethods();

        assertEquals(2, methods.length);
    }
}
