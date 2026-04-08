package com.openbanking.yos.domain.account.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountEntityTest {

    @Test
    void shouldCreateAccountEntityWithBuilder() {
        String hspRef = "hsp-123";
        String rizaNo = "riza-456";
        String hspNo = "TR1234567890";
        String hspShb = "John Doe";
        String prBrm = "TRY";
        String hspTur = "B";
        String hspTip = "VADESIZ";
        String hspDrm = "AKTIF";
        LocalDateTime hspAclsTrh = LocalDateTime.now();

        AccountEntity entity = AccountEntity.builder()
                .id(1L)
                .hspRef(hspRef)
                .rizaNo(rizaNo)
                .hspNo(hspNo)
                .hspShb(hspShb)
                .subeAdi("Merkez")
                .kisaAd("Maaş Hesabı")
                .prBrm(prBrm)
                .hspTur(hspTur)
                .hspTip(hspTip)
                .hspUrunAdi("Altın Hesap")
                .hspDrm(hspDrm)
                .hspAclsTrh(hspAclsTrh)
                .hhsKod("8000")
                .yosKod("9000")
                .build();

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(hspRef, entity.getHspRef());
        assertEquals(rizaNo, entity.getRizaNo());
        assertEquals(hspNo, entity.getHspNo());
        assertEquals(hspShb, entity.getHspShb());
        assertEquals("Merkez", entity.getSubeAdi());
        assertEquals("Maaş Hesabı", entity.getKisaAd());
        assertEquals(prBrm, entity.getPrBrm());
        assertEquals(hspTur, entity.getHspTur());
        assertEquals(hspTip, entity.getHspTip());
        assertEquals("Altın Hesap", entity.getHspUrunAdi());
        assertEquals(hspDrm, entity.getHspDrm());
        assertEquals(hspAclsTrh, entity.getHspAclsTrh());
        assertEquals("8000", entity.getHhsKod());
        assertEquals("9000", entity.getYosKod());
    }

    @Test
    void shouldCreateAccountEntityWithNoArgsConstructor() {
        AccountEntity entity = new AccountEntity();
        String hspRef = "hsp-789";
        entity.setHspRef(hspRef);

        assertNotNull(entity);
        assertEquals(hspRef, entity.getHspRef());
    }
}
