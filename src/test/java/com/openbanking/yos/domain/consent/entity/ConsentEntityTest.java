package com.openbanking.yos.domain.consent.entity;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.GkdType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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
                .rizaIptDtyKod("01")
                .yetYntm(yetYntm)
                .yetTmmZmn(yetTmmZmn)
                .yonAdr("https://yos.com/redirect")
                .hhsYonAdr("https://hhs.com/auth")
                .ohkTur("B")
                .kmlkTur("K")
                .kmlkVrs("11111111110")
                .prBrm("TRY")
                .ttr("500.00")
                .gonUnv("Gönderen A.Ş.")
                .gonHspNo("TR123456789012345678901234")
                .alcUnv("Alıcı Unvan")
                .alcHspNo("TR123456")
                .odmKynk("O")
                .odmAmc("01")
                .refBlg("REF-001")
                .build();

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(rizaNo, entity.getRizaNo());
        assertEquals(hhsKod, entity.getHhsKod());
        assertEquals(yosKod, entity.getYosKod());
        assertEquals(rizaDrm, entity.getRizaDrm());
        assertEquals("01", entity.getRizaIptDtyKod());
        assertEquals(yetYntm, entity.getYetYntm());
        assertEquals(yetTmmZmn, entity.getYetTmmZmn());
        assertEquals("https://yos.com/redirect", entity.getYonAdr());
        assertEquals("https://hhs.com/auth", entity.getHhsYonAdr());
        assertEquals("B", entity.getOhkTur());
        assertEquals("K", entity.getKmlkTur());
        assertEquals("11111111110", entity.getKmlkVrs());
        assertEquals("TRY", entity.getPrBrm());
        assertEquals("500.00", entity.getTtr());
        assertEquals("Gönderen A.Ş.", entity.getGonUnv());
        assertEquals("TR123456789012345678901234", entity.getGonHspNo());
        assertEquals("Alıcı Unvan", entity.getAlcUnv());
        assertEquals("TR123456", entity.getAlcHspNo());
        assertEquals("O", entity.getOdmKynk());
        assertEquals("01", entity.getOdmAmc());
        assertEquals("REF-001", entity.getRefBlg());
    }
    
    @Test
    void shouldCreateConsentEntityWithNoArgsConstructor() {
        ConsentEntity entity = new ConsentEntity();
        String rizaNo = "riza-67890";
        entity.setRizaNo(rizaNo);

        assertNotNull(entity);
        assertEquals(rizaNo, entity.getRizaNo());
    }
    
    @Test
    void shouldCreateConsentEntityWithAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        ConsentEntity entity = new ConsentEntity(1L, "riza", "8000", "9000", ConsentStatus.B, "01", now, now, now, GkdType.Y, "yon", "hhs", "B", "K", "123", "TRY", "100", "gon", "gonHsp", "alc", "alcHsp", "O", "01", "ref");

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("riza", entity.getRizaNo());
        assertEquals("8000", entity.getHhsKod());
        assertEquals("9000", entity.getYosKod());
        assertEquals(ConsentStatus.B, entity.getRizaDrm());
        assertEquals("01", entity.getRizaIptDtyKod());
        assertEquals(GkdType.Y, entity.getYetYntm());
        assertEquals("yon", entity.getYonAdr());
        assertEquals("hhs", entity.getHhsYonAdr());
        assertEquals("B", entity.getOhkTur());
        assertEquals("K", entity.getKmlkTur());
        assertEquals("123", entity.getKmlkVrs());
        assertEquals("TRY", entity.getPrBrm());
        assertEquals("100", entity.getTtr());
        assertEquals("gon", entity.getGonUnv());
        assertEquals("gonHsp", entity.getGonHspNo());
        assertEquals("alc", entity.getAlcUnv());
        assertEquals("alcHsp", entity.getAlcHspNo());
        assertEquals("O", entity.getOdmKynk());
        assertEquals("01", entity.getOdmAmc());
        assertEquals("ref", entity.getRefBlg());
    }
    
    @Test
    void shouldTestEqualsAndHashCode() {
        ConsentEntity entity1 = ConsentEntity.builder().id(1L).rizaNo("riza").build();
        ConsentEntity entity2 = ConsentEntity.builder().id(1L).rizaNo("riza").build();
        ConsentEntity entity3 = ConsentEntity.builder().id(2L).rizaNo("farkli").build();

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
        assertNotEquals(entity1, entity3);
    }
    @Test
    void shouldTestToString() {
        ConsentEntity entity = ConsentEntity.builder().id(1L).rizaNo("riza").build();
        String toString = entity.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("rizaNo=riza"));
}
