package com.openbanking.yos.domain.payment.entity;

import com.openbanking.yos.common.enums.GkdType;
import com.openbanking.yos.common.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityTest {

    @Test
    void shouldCreatePaymentEntityWithBuilder() {
        String odmEmriNo = "odm-123";
        String rizaNo = "riza-456";
        PaymentStatus odmDrm = PaymentStatus.G;
        GkdType yetYntm = GkdType.Y;
        String alcUnv = "Ali Yilmaz";
        String alcHspNo = "TR123456";

        PaymentEntity entity = PaymentEntity.builder()
                .id(1L)
                .odmEmriNo(odmEmriNo)
                .rizaNo(rizaNo)
                .odmDrm(odmDrm)
                .yetYntm(yetYntm)
                .alcUnv(alcUnv)
                .alcHspNo(alcHspNo)
                .odmKynk("O")
                .odmAmc("01")
                .hhsKod("8000")
                .yosKod("9000")
                .build();

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(odmEmriNo, entity.getOdmEmriNo());
        assertEquals(rizaNo, entity.getRizaNo());
        assertEquals(odmDrm, entity.getOdmDrm());
        assertEquals(yetYntm, entity.getYetYntm());
        assertEquals(alcUnv, entity.getAlcUnv());
        assertEquals(alcHspNo, entity.getAlcHspNo());
        assertEquals("8000", entity.getHhsKod());
        assertEquals("9000", entity.getYosKod());
    }

    @Test
    void shouldCreatePaymentEntityWithNoArgsConstructor() {
        PaymentEntity entity = new PaymentEntity();
        String odmEmriNo = "odm-789";
        entity.setOdmEmriNo(odmEmriNo);

        assertNotNull(entity);
        assertEquals(odmEmriNo, entity.getOdmEmriNo());
    }

    @Test
    void shouldCreatePaymentEntityWithAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        PaymentEntity entity = new PaymentEntity(1L, "odm", "riza", PaymentStatus.G, "8000", "9000", now, now, GkdType.Y, "yon", "hhs", "B", "K", "123", "TRY", "100", "gon", "gonHsp", "alc", "alcHsp", "O", "01", "ref", "ack");

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("odm", entity.getOdmEmriNo());
    }

    @Test
    void shouldTestEqualsAndHashCode() {
        PaymentEntity entity1 = PaymentEntity.builder().id(1L).odmEmriNo("odm").build();
        PaymentEntity entity2 = PaymentEntity.builder().id(1L).odmEmriNo("odm").build();
        PaymentEntity entity3 = PaymentEntity.builder().id(2L).odmEmriNo("farkli").build();

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
        assertNotEquals(entity1, entity3);
    }

    @Test
    void shouldTestToString() {
        PaymentEntity entity = PaymentEntity.builder().id(1L).odmEmriNo("odm").build();
        String toString = entity.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("odmEmriNo=odm"));
    }
}
