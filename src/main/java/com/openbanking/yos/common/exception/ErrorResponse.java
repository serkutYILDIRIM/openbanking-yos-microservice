package com.openbanking.yos.common.exception;

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
public class ErrorResponse {

    @JsonProperty("httpKod")
    private int httpKod;

    @JsonProperty("httpAcklm")
    private String httpAcklm;

    @JsonProperty("morTnmKod")
    private String morTnmKod;

    @JsonProperty("morTnmAcklm")
    private String morTnmAcklm;

    @JsonProperty("zaman")
    private LocalDateTime zaman;
}
