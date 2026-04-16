package com.openbanking.yos.domain.payment.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRequestTest {

    @Test
    @DisplayName("PaymentRequest builder ile başarıyla oluşturulmalı")
    void shouldCreatePaymentRequestWithBuilder() {
        PaymentRequest.RizaBilgileri rzBlg = PaymentRequest.RizaBilgileri.builder()
                .rizaNo("riza-123")
                .build();

        PaymentRequest.KatilimciBilgisi katilimciBlg = PaymentRequest.KatilimciBilgisi.builder()
                .hhsKod("HHS01")
                .yosKod("YOS01")
                .build();

        PaymentRequest.Kimlik kmlk = PaymentRequest.Kimlik.builder()
                .kmlkTur("TCKN")
                .kmlkVrs("12345678901")
                .ohkTur("B")
                .build();

        PaymentRequest.Tutar islTtr = PaymentRequest.Tutar.builder()
                .prBrm("TRY")
                .ttr("100.50")
                .build();

        PaymentRequest.Hesap alc = PaymentRequest.Hesap.builder()
                .unv("Alıcı Ünvan")
                .hspNo("TR1234567890")
                .build();

        PaymentRequest.OdemeAyrintilari odmAyr = PaymentRequest.OdemeAyrintilari.builder()
                .odmKynk("A")
                .odmAmc("O")
                .build();

        PaymentRequest.OdemeBaslatma odmBsltm = PaymentRequest.OdemeBaslatma.builder()
                .kmlk(kmlk)
                .islTtr(islTtr)
                .alc(alc)
                .odmAyr(odmAyr)
                .build();

        PaymentRequest request = PaymentRequest.builder()
                .rzBlg(rzBlg)
                .katilimciBlg(katilimciBlg)
                .odmBsltm(odmBsltm)
                .build();

        assertNotNull(request);
        assertEquals("riza-123", request.getRzBlg().getRizaNo());
        assertEquals("HHS01", request.getKatilimciBlg().getHhsKod());
        assertEquals("YOS01", request.getKatilimciBlg().getYosKod());
        assertNotNull(request.getOdmBsltm());
        assertEquals("TCKN", request.getOdmBsltm().getKmlk().getKmlkTur());
        assertEquals("100.50", request.getOdmBsltm().getIslTtr().getTtr());
        assertEquals("TR1234567890", request.getOdmBsltm().getAlc().getHspNo());
    }

    @Test
    @DisplayName("PaymentRequest no-args constructor ve setter'lar çalışmalı")
    void shouldCreatePaymentRequestWithSetters() {
        PaymentRequest request = new PaymentRequest();
        PaymentRequest.RizaBilgileri rzBlg = new PaymentRequest.RizaBilgileri();
        rzBlg.setRizaNo("riza-456");

        request.setRzBlg(rzBlg);

        assertEquals("riza-456", request.getRzBlg().getRizaNo());
    }
}
