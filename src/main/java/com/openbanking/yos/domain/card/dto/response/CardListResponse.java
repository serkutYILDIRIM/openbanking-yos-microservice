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
public class CardListResponse {

    @JsonProperty("kartlar")
    private List<CardResponse> kartlar;
}

