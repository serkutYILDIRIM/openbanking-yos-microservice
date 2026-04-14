package com.openbanking.yos.domain.card.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {

    @JsonProperty("rizaNo")
    private String rizaNo;

    @JsonProperty("kartRef")
    private String kartRef;

    @JsonProperty("kartNo")
    private String kartNo;

    @JsonProperty("asilKartNo")
    private String asilKartNo;

    @JsonProperty("kartTipi")
    private String kartTipi;

    @JsonProperty("altKartTipi")
    private String altKartTipi;

    @JsonProperty("kartFormu")
    private String kartFormu;

    @JsonProperty("kartTuru")
    private String kartTuru;

    @JsonProperty("kartStatu")
    private String kartStatu;

    @JsonProperty("kartSahibi")
    private String kartSahibi;

    @JsonProperty("kartTicariUnvan")
    private String kartTicariUnvan;

    @JsonProperty("kartUrunAdi")
    private String kartUrunAdi;

    @JsonProperty("ekstreTurleri")
    private List<EkstreTur> ekstreTurleri;

    @JsonProperty("kartRumuz")
    private String kartRumuz;

    @JsonProperty("kartSema")
    private String kartSema;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EkstreTur {

        @JsonProperty("ekstreStatu")
        private String ekstreStatu;

        @JsonProperty("paraBirimi")
        private String paraBirimi;
    }
}

