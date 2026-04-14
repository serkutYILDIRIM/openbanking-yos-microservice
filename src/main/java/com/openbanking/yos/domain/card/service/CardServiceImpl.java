package com.openbanking.yos.domain.card.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.card.dto.response.CardListResponse;
import com.openbanking.yos.domain.card.dto.response.CardResponse;
import com.openbanking.yos.domain.card.entity.CardEntity;
import com.openbanking.yos.domain.card.repository.CardRepository;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ConsentRepository consentRepository;

    @Override
    public CardListResponse getCards(String xAspspCode, String xTppCode, String rizaNo) {

        ConsentEntity consent = consentRepository.findByRizaNo(rizaNo)
                .orElseThrow(() -> new OhvpsException("TR.OHVPS.Resource.NotFound"));

        if (!consent.getHhsKod().equals(xAspspCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidASPSP");
        }

        if (!consent.getYosKod().equals(xTppCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidTPP");
        }

        if (consent.getRizaDrm() != ConsentStatus.K && consent.getRizaDrm() != ConsentStatus.E) {
            throw new OhvpsException("TR.OHVPS.Resource.ConsentMismatch");
        }

        List<CardEntity> cards = cardRepository.findByRizaNo(rizaNo);

        List<CardResponse> cardResponses = cards.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return CardListResponse.builder()
                .kartlar(cardResponses)
                .build();
    }

    @Override
    public CardResponse getCard(String kartRef, String xAspspCode, String xTppCode, String rizaNo) {

        CardEntity card = cardRepository.findByKartRef(kartRef)
                .orElseThrow(() -> new OhvpsException("TR.OHVPS.Resource.NotFound"));

        ConsentEntity consent = consentRepository.findByRizaNo(rizaNo)
                .orElseThrow(() -> new OhvpsException("TR.OHVPS.Resource.NotFound"));

        if (!consent.getHhsKod().equals(xAspspCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidASPSP");
        }

        if (!consent.getYosKod().equals(xTppCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidTPP");
        }

        if (consent.getRizaDrm() != ConsentStatus.K && consent.getRizaDrm() != ConsentStatus.E) {
            throw new OhvpsException("TR.OHVPS.Resource.ConsentMismatch");
        }

        if (!card.getRizaNo().equals(rizaNo)) {
            throw new OhvpsException("TR.OHVPS.Resource.NotFound");
        }

        return toResponse(card);
    }

    private CardResponse toResponse(CardEntity entity) {

        return CardResponse.builder()
                .rizaNo(entity.getRizaNo())
                .kartRef(entity.getKartRef())
                .kartNo(entity.getKartNo())
                .asilKartNo(entity.getAsilKartNo())
                .kartTipi(entity.getKartTipi())
                .altKartTipi(entity.getAltKartTipi())
                .kartFormu(entity.getKartFormu())
                .kartTuru(entity.getKartTuru())
                .kartStatu(entity.getKartStatu())
                .kartSahibi(entity.getKartSahibi())
                .kartTicariUnvan(entity.getKartTicariUnvan())
                .kartUrunAdi(entity.getKartUrunAdi())
                .ekstreTurleri(null)
                .kartRumuz(entity.getKartRumuz())
                .kartSema(entity.getKartSema())
                .build();
    }
}

