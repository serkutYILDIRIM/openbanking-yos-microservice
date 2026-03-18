package com.openbanking.yos.domain.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotNull
    @Valid
    @JsonProperty("rzBlg")
    private RizaBilgileri rzBlg;

    @NotNull
    @Valid
    @JsonProperty("katilimciBlg")
    private KatilimciBilgisi katilimciBlg;

    @NotNull
    @Valid
    @JsonProperty("odmBsltm")
    private OdemeBaslatma odmBsltm;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RizaBilgileri {

        @NotBlank
        @JsonProperty("rizaNo")
        private String rizaNo;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KatilimciBilgisi {

        @NotBlank
        @JsonProperty("hhsKod")
        private String hhsKod;

        @NotBlank
        @JsonProperty("yosKod")
        private String yosKod;
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

        @NotBlank
        @JsonProperty("ohkTur")
        private String ohkTur;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tutar {

        @NotBlank
        @JsonProperty("prBrm")
        private String prBrm;

        @NotBlank
        @Pattern(regexp = "^\\d{1,18}$|^\\d{1,18}\\.\\d{1,5}$")
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

        @NotBlank
        @JsonProperty("odmKynk")
        private String odmKynk;

        @NotBlank
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

        @NotNull
        @Valid
        @JsonProperty("kmlk")
        private Kimlik kmlk;

        @NotNull
        @Valid
        @JsonProperty("islTtr")
        private Tutar islTtr;

        @Valid
        @JsonProperty("gon")
        private Hesap gon;

        @NotNull
        @Valid
        @JsonProperty("alc")
        private Hesap alc;

        @NotNull
        @Valid
        @JsonProperty("odmAyr")
        private OdemeAyrintilari odmAyr;
    }
}

