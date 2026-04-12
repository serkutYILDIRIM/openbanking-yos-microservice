package com.openbanking.yos.domain.consent.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsentRequestTest {

    @Test
    void shouldCreateConsentRequestWithBuilder() {
        ConsentRequest.KatilimciBilgisi katilimciBlg = ConsentRequest.KatilimciBilgisi.builder()
                .hhsKod("8000")
                .yosKod("9000")
                .build();

        ConsentRequest.Gkd gkd = ConsentRequest.Gkd.builder()
                .yetYntm("Y")
                .yonAdr("https://example.com")
                .build();

        ConsentRequest.Kimlik kmlk = ConsentRequest.Kimlik.builder()
                .ohkTur("B")
                .kmlkTur("K")
                .kmlkVrs("11111111110")
                .build();

        ConsentRequest.Tutar islTtr = ConsentRequest.Tutar.builder()
                .prBrm("TRY")
                .ttr("100.50")
                .build();

        ConsentRequest.Hesap alc = ConsentRequest.Hesap.builder()
                .unv("Ali Yilmaz")
                .hspNo("TR1234")
                .build();

        ConsentRequest.OdemeAyrintilari odmAyr = ConsentRequest.OdemeAyrintilari.builder()
                .odmKynk("O")
                .odmAmc("01")
                .build();

        ConsentRequest.OdemeBaslatma odmBsltm = ConsentRequest.OdemeBaslatma.builder()
                .kmlk(kmlk)
                .islTtr(islTtr)
                .alc(alc)
                .odmAyr(odmAyr)
                .build();

        ConsentRequest request = ConsentRequest.builder()
                .katilimciBlg(katilimciBlg)
                .gkd(gkd)
                .odmBsltm(odmBsltm)
                .build();

        assertNotNull(request);
        assertEquals("8000", request.getKatilimciBlg().getHhsKod());
        assertEquals("Y", request.getGkd().getYetYntm());
        assertEquals("100.50", request.getOdmBsltm().getIslTtr().getTtr());
    }

    @Test
    void shouldTestNestedClassesNoArgsConstructor() {
        ConsentRequest.KatilimciBilgisi kb = new ConsentRequest.KatilimciBilgisi();
        kb.setHhsKod("8000");
        assertEquals("8000", kb.getHhsKod());

        ConsentRequest.Gkd gkd = new ConsentRequest.Gkd();
        gkd.setYetYntm("A");
        assertEquals("A", gkd.getYetYntm());

        ConsentRequest.Kimlik kmlk = new ConsentRequest.Kimlik();
        kmlk.setOhkTur("T");
        assertEquals("T", kmlk.getOhkTur());

        ConsentRequest.Tutar tutar = new ConsentRequest.Tutar();
        tutar.setTtr("50");
        assertEquals("50", tutar.getTtr());

        ConsentRequest.Hesap hesap = new ConsentRequest.Hesap();
        hesap.setUnv("Veli");
        assertEquals("Veli", hesap.getUnv());

        ConsentRequest.OdemeAyrintilari ayr = new ConsentRequest.OdemeAyrintilari();
        ayr.setOdmKynk("I");
        assertEquals("I", ayr.getOdmKynk());
    }
}
