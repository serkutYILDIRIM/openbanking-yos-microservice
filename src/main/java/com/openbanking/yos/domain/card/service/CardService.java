package com.openbanking.yos.domain.card.service;

import com.openbanking.yos.domain.card.dto.response.CardListResponse;
import com.openbanking.yos.domain.card.dto.response.CardResponse;

public interface CardService {

    CardListResponse getCards(String xAspspCode, String xTppCode, String rizaNo);

    CardResponse getCard(String kartRef, String xAspspCode, String xTppCode, String rizaNo);
}

