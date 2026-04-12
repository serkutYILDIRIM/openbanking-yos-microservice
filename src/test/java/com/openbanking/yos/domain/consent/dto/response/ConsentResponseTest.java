package com.openbanking.yos.domain.consent.dto.response;

import com.openbanking.yos.common.enums.ConsentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsentResponseTest {

    @Test
    void shouldCreateConsentResponseWithBuilder() {
        LocalDateTime now = LocalDateTime.now();
        ConsentResponse.RizaBilgileri rzBlg = ConsentResponse.RizaBilgileri.builder()
                .rizaNo("riza-123")
                .rizaDrm(ConsentStatus.E)
                .olusZmn(now)
                .gnclZmn(now)
                .build();

        ConsentResponse.KatilimciBilgisi katilimciBlg = ConsentResponse.KatilimciBilgisi.builder()
                .hhsKod("8000")
                .yosKod("9000")
                .build();

        ConsentResponse.Gkd gkd = ConsentResponse.Gkd.builder()
                .yetYntm("Y")
                .hhsYonAdr("https://hhs.example.com")
                .build();

        ConsentResponse response = ConsentResponse.builder()
                .rzBlg(rzBlg)
                .katilimciBlg(katilimciBlg)
                .gkd(gkd)
                .build();

        assertNotNull(response);
        assertEquals("riza-123", response.getRzBlg().getRizaNo());
        assertEquals(ConsentStatus.E, response.getRzBlg().getRizaDrm());
        assertEquals("8000", response.getKatilimciBlg().getHhsKod());
        assertEquals("Y", response.getGkd().getYetYntm());
    }

    @Test
    void shouldTestNestedClassesNoArgsConstructor() {
        ConsentResponse.RizaBilgileri rz = new ConsentResponse.RizaBilgileri();
        rz.setRizaNo("123");
        assertEquals("123", rz.getRizaNo());

        ConsentResponse.KatilimciBilgisi kb = new ConsentResponse.KatilimciBilgisi();
        kb.setHhsKod("8000");
        assertEquals("8000", kb.getHhsKod());

        ConsentResponse.Gkd gkd = new ConsentResponse.Gkd();
        gkd.setYetYntm("A");
        assertEquals("A", gkd.getYetYntm());

        ConsentResponse.Kimlik kmlk = new ConsentResponse.Kimlik();
        kmlk.setOhkTur("B");
        assertEquals("B", kmlk.getOhkTur());

        ConsentResponse.Tutar tutar = new ConsentResponse.Tutar();
        tutar.setTtr("100");
        assertEquals("100", tutar.getTtr());

        ConsentResponse.Hesap hesap = new ConsentResponse.Hesap();
        hesap.setUnv("Test");
        assertEquals("Test", hesap.getUnv());

        ConsentResponse.OdemeAyrintilari ayr = new ConsentResponse.OdemeAyrintilari();
        ayr.setOdmKynk("O");
        assertEquals("O", ayr.getOdmKynk());
    }
}
