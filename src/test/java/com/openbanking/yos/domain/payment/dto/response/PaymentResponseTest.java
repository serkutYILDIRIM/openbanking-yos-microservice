package com.openbanking.yos.domain.payment.dto.response;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentResponseTest {

    @Test
    @DisplayName("PaymentResponse builder ve getter metodları düzgün çalışmalı")
    void shouldWorkWithBuilderAndGetters() {
        LocalDateTime now = LocalDateTime.now();

        PaymentResponse.RizaBilgileri rzBlg = PaymentResponse.RizaBilgileri.builder()
                .rizaNo("riza-123")
                .olusZmn(now)
                .gnclZmn(now)
                .rizaDrm(ConsentStatus.E)
                .build();

        PaymentResponse.KatilimciBilgisi katilimciBlg = PaymentResponse.KatilimciBilgisi.builder()
                .hhsKod("HHS01")
                .yosKod("YOS01")
                .build();

        PaymentResponse.Gkd gkd = PaymentResponse.Gkd.builder()
                .yetYntm("Y")
                .yonAdr("http://yos.com/callback")
                .hhsYonAdr("http://hhs.com/auth")
                .build();

        PaymentResponse.Kimlik kmlk = PaymentResponse.Kimlik.builder()
                .kmlkTur("T.C. Kimlik Numarası")
                .kmlkVrs("12345678901")
                .ohkTur("B")
                .build();

        PaymentResponse.Tutar islTtr = PaymentResponse.Tutar.builder()
                .prBrm("TRY")
                .ttr("100.50")
                .build();

        PaymentResponse.Hesap gon = PaymentResponse.Hesap.builder()
                .unv("Gönderen A.Ş.")
                .hspNo("TR12345")
                .build();

        PaymentResponse.Hesap alc = PaymentResponse.Hesap.builder()
                .unv("Alıcı A.Ş.")
                .hspNo("TR67890")
                .build();

        PaymentResponse.OdemeAyrintilari odmAyr = PaymentResponse.OdemeAyrintilari.builder()
                .odmKynk("H")
                .odmAmc("T")
                .refBlg("REF-001")
                .odmAcklm("Test ödemesi")
                .build();

        PaymentResponse.OdemeBaslatma odmBsltm = PaymentResponse.OdemeBaslatma.builder()
                .kmlk(kmlk)
                .islTtr(islTtr)
                .gon(gon)
                .alc(alc)
                .odmAyr(odmAyr)
                .build();

        PaymentResponse.OdemeBilgileri odmBlg = PaymentResponse.OdemeBilgileri.builder()
                .odmEmriNo("odm-789")
                .olusZmn(now)
                .gnclZmn(now)
                .odmDrm(PaymentStatus.G)
                .build();

        PaymentResponse response = PaymentResponse.builder()
                .rzBlg(rzBlg)
                .katilimciBlg(katilimciBlg)
                .gkd(gkd)
                .odmBsltm(odmBsltm)
                .odmBlg(odmBlg)
                .build();

        assertNotNull(response);
        assertEquals(rzBlg, response.getRzBlg());
        assertEquals(katilimciBlg, response.getKatilimciBlg());
        assertEquals(gkd, response.getGkd());
        assertEquals(odmBsltm, response.getOdmBsltm());
        assertEquals(odmBlg, response.getOdmBlg());

        assertEquals("riza-123", response.getRzBlg().getRizaNo());
        assertEquals(now, response.getRzBlg().getOlusZmn());
        assertEquals(ConsentStatus.E, response.getRzBlg().getRizaDrm());
        assertEquals("HHS01", response.getKatilimciBlg().getHhsKod());
        assertEquals("YOS01", response.getKatilimciBlg().getYosKod());
        assertEquals("Y", response.getGkd().getYetYntm());
        assertEquals("T.C. Kimlik Numarası", response.getOdmBsltm().getKmlk().getKmlkTur());
        assertEquals("TRY", response.getOdmBsltm().getIslTtr().getPrBrm());
        assertEquals("Gönderen A.Ş.", response.getOdmBsltm().getGon().getUnv());
        assertEquals("Alıcı A.Ş.", response.getOdmBsltm().getAlc().getUnv());
        assertEquals("REF-001", response.getOdmBsltm().getOdmAyr().getRefBlg());
        assertEquals("odm-789", response.getOdmBlg().getOdmEmriNo());
        assertEquals(PaymentStatus.G, response.getOdmBlg().getOdmDrm());
    }

    @Test
    @DisplayName("PaymentResponse setter metodları düzgün çalışmalı")
    void shouldWorkWithSetters() {
        PaymentResponse response = new PaymentResponse();
        PaymentResponse.RizaBilgileri rzBlg = new PaymentResponse.RizaBilgileri();
        rzBlg.setRizaNo("riza-set");

        response.setRzBlg(rzBlg);

        assertEquals("riza-set", response.getRzBlg().getRizaNo());
    }

    @Test
    @DisplayName("PaymentResponse equals ve hashCode metodları düzgün çalışmalı")
    void shouldWorkWithEqualsAndHashCode() {
        PaymentResponse response1 = PaymentResponse.builder()
                .odmBlg(PaymentResponse.OdemeBilgileri.builder().odmEmriNo("123").build())
                .build();
        PaymentResponse response2 = PaymentResponse.builder()
                .odmBlg(PaymentResponse.OdemeBilgileri.builder().odmEmriNo("123").build())
                .build();
        PaymentResponse response3 = PaymentResponse.builder()
                .odmBlg(PaymentResponse.OdemeBilgileri.builder().odmEmriNo("456").build())
                .build();

        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }
}
