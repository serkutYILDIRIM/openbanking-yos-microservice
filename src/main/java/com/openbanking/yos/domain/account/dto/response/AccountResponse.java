package com.openbanking.yos.domain.account.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    @JsonProperty("rizaNo")
    private String rizaNo;

    @JsonProperty("hspTml")
    private HesapTemel hspTml;

    @JsonProperty("hspDty")
    private HesapDetay hspDty;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HesapTemel {

        @JsonProperty("hspRef")
        private String hspRef;

        @JsonProperty("hspNo")
        private String hspNo;

        @JsonProperty("hspShb")
        private String hspShb;

        @JsonProperty("subeAdi")
        private String subeAdi;

        @JsonProperty("kisaAd")
        private String kisaAd;

        @JsonProperty("prBrm")
        private String prBrm;

        @JsonProperty("hspTur")
        private String hspTur;

        @JsonProperty("hspTip")
        private String hspTip;

        @JsonProperty("hspUrunAdi")
        private String hspUrunAdi;

        @JsonProperty("hspDrm")
        private String hspDrm;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HesapDetay {

        @JsonProperty("hspAclsTrh")
        private LocalDateTime hspAclsTrh;
    }
}

