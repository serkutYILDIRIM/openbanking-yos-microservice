package com.openbanking.yos.domain.account.dto.response;

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
public class AccountListResponse {

    @JsonProperty("hesaplar")
    private List<AccountResponse> hesaplar;
}

