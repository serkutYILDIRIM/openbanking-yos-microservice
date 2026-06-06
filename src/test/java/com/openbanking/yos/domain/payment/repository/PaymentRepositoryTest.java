package com.openbanking.yos.domain.payment.repository;

import com.openbanking.yos.domain.payment.entity.PaymentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    @Test
    void shouldBeInterfaceExtendingJpaRepository() {
        assertTrue(PaymentRepository.class.isInterface());
        assertTrue(JpaRepository.class.isAssignableFrom(PaymentRepository.class));
    }

    @Test
    void shouldHaveFindByOdmEmriNoMethod() throws NoSuchMethodException {
        Method method = PaymentRepository.class.getDeclaredMethod("findByOdmEmriNo", String.class);

        assertNotNull(method);
        assertEquals(Optional.class, method.getReturnType());
    }

    @Test
    void shouldHaveCorrectGenericTypes() {
        Class<?>[] interfaces = PaymentRepository.class.getInterfaces();

        assertEquals(1, interfaces.length);
        assertEquals(JpaRepository.class, interfaces[0]);
    }

    @Test
    void shouldHaveEntityTypeAsPaymentEntity() {
        java.lang.reflect.Type[] genericInterfaces = PaymentRepository.class.getGenericInterfaces();

        assertNotNull(genericInterfaces);
        assertEquals(1, genericInterfaces.length);
        String typeName = genericInterfaces[0].getTypeName();
        assertTrue(typeName.contains(PaymentEntity.class.getName()));
        assertTrue(typeName.contains(Long.class.getName()));
    }

    @Test
    void shouldBeInCorrectPackage() {
        assertEquals("com.openbanking.yos.domain.payment.repository", PaymentRepository.class.getPackageName());
    }

    @Test
    void shouldHaveExactlyOneDeclaredMethod() {
        Method[] methods = PaymentRepository.class.getDeclaredMethods();

        assertEquals(1, methods.length);
    }
}
