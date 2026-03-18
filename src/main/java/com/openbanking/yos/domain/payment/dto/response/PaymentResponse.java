package com.openbanking.yos.domain.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    @JsonProperty("rzBlg")
    private RizaBilgileri rzBlg;

    @JsonProperty("katilimciBlg")
    private KatilimciBilgisi katilimciBlg;

    @JsonProperty("gkd")
    private Gkd gkd;

    @JsonProperty("odmBsltm")
    private OdemeBaslatma odmBsltm;

    @JsonProperty("odmBlg")
    private OdemeBilgileri odmBlg;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RizaBilgileri {

        @JsonProperty("rizaNo")
        private String rizaNo;

        @JsonProperty("olusZmn")
        private LocalDateTime olusZmn;

        @JsonProperty("gnclZmn")
        private LocalDateTime gnclZmn;

        @JsonProperty("rizaDrm")
        private ConsentStatus rizaDrm;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KatilimciBilgisi {

        @JsonProperty("hhsKod")
        private String hhsKod;

        @JsonProperty("yosKod")
        private String yosKod;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Gkd {

        @JsonProperty("yetYntm")
        private String yetYntm;

        @JsonProperty("yonAdr")
        private String yonAdr;

        @JsonProperty("hhsYonAdr")
        private String hhsYonAdr;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Kimlik {

        @JsonProperty("kmlkTur")
        private String kmlkTur;

        @JsonProperty("kmlkVrs")
        private String kmlkVrs;

        @JsonProperty("ohkTur")
        private String ohkTur;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tutar {

        @JsonProperty("prBrm")
        private String prBrm;

        @JsonProperty("ttr")
        private String ttr;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hesap {

        @JsonProperty("unv")
        private String unv;

        @JsonProperty("hspNo")
        private String hspNo;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OdemeAyrintilari {

        @JsonProperty("odmKynk")
        private String odmKynk;

        @JsonProperty("odmAmc")
        private String odmAmc;

        @JsonProperty("refBlg")
        private String refBlg;

        @JsonProperty("odmAcklm")
        private String odmAcklm;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OdemeBaslatma {

        @JsonProperty("kmlk")
        private Kimlik kmlk;

        @JsonProperty("islTtr")
        private Tutar islTtr;

        @JsonProperty("gon")
        private Hesap gon;

        @JsonProperty("alc")
        private Hesap alc;

        @JsonProperty("odmAyr")
        private OdemeAyrintilari odmAyr;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OdemeBilgileri {

        @JsonProperty("odmEmriNo")
        private String odmEmriNo;

        @JsonProperty("olusZmn")
        private LocalDateTime olusZmn;

        @JsonProperty("gnclZmn")
        private LocalDateTime gnclZmn;

        @JsonProperty("odmDrm")
        private PaymentStatus odmDrm;
    }
}

