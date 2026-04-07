package com.openbanking.yos.domain.consent.entity;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.GkdType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsentEntityTest {

    @Test
    void shouldCreateConsentEntityWithBuilder() {
        String rizaNo = "riza-12345";
        String hhsKod = "1234";
        String yosKod = "5678";
        ConsentStatus rizaDrm = ConsentStatus.E;
        GkdType yetYntm = GkdType.Y;
        LocalDateTime yetTmmZmn = LocalDateTime.now();

        ConsentEntity entity = ConsentEntity.builder()
                .id(1L)
                .rizaNo(rizaNo)
                .hhsKod(hhsKod)
                .yosKod(yosKod)
                .rizaDrm(rizaDrm)
                .yetYntm(yetYntm)
                .yetTmmZmn(yetTmmZmn)
                .alcUnv("Alıcı Unvan")
                .alcHspNo("TR123456")
                .odmKynk("O")
                .odmAmc("01")
                .build();

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(rizaNo, entity.getRizaNo());
        assertEquals(hhsKod, entity.getHhsKod());
        assertEquals(yosKod, entity.getYosKod());
        assertEquals(rizaDrm, entity.getRizaDrm());
        assertEquals(yetYntm, entity.getYetYntm());
        assertEquals(yetTmmZmn, entity.getYetTmmZmn());
        assertEquals("Alıcı Unvan", entity.getAlcUnv());
        assertEquals("TR123456", entity.getAlcHspNo());
        assertEquals("O", entity.getOdmKynk());
        assertEquals("01", entity.getOdmAmc());
    }

    @Test
    void shouldCreateConsentEntityWithNoArgsConstructor() {
        ConsentEntity entity = new ConsentEntity();
        String rizaNo = "riza-67890";
        entity.setRizaNo(rizaNo);

        assertNotNull(entity);
        assertEquals(rizaNo, entity.getRizaNo());
    }
}
