package com.openbanking.yos.domain.account.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.account.dto.response.AccountListResponse;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;
import com.openbanking.yos.domain.account.entity.AccountEntity;
import com.openbanking.yos.domain.account.repository.AccountRepository;
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
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ConsentRepository consentRepository;

    @Override
    public AccountListResponse getAccounts(String xAspspCode, String xTppCode, String rizaNo) {

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

        List<AccountEntity> accounts = accountRepository.findByRizaNo(rizaNo);

        List<AccountResponse> accountResponses = accounts.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return AccountListResponse.builder()
                .hesaplar(accountResponses)
                .build();
    }

    @Override
    public AccountResponse getAccount(String hspRef, String xAspspCode, String xTppCode, String rizaNo) {

        AccountEntity account = accountRepository.findByHspRef(hspRef)
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

        if (!account.getRizaNo().equals(rizaNo)) {
            throw new OhvpsException("TR.OHVPS.Resource.NotFound");
        }

        return toResponse(account);
    }

    private AccountResponse toResponse(AccountEntity entity) {

        AccountResponse.HesapTemel hspTml = AccountResponse.HesapTemel.builder()
                .hspRef(entity.getHspRef())
                .hspNo(entity.getHspNo())
                .hspShb(entity.getHspShb())
                .subeAdi(entity.getSubeAdi())
                .kisaAd(entity.getKisaAd())
                .prBrm(entity.getPrBrm())
                .hspTur(entity.getHspTur())
                .hspTip(entity.getHspTip())
                .hspUrunAdi(entity.getHspUrunAdi())
                .hspDrm(entity.getHspDrm())
                .build();

        AccountResponse.HesapDetay hspDty = entity.getHspAclsTrh() != null
                ? AccountResponse.HesapDetay.builder()
                        .hspAclsTrh(entity.getHspAclsTrh())
                        .build()
                : null;

        return AccountResponse.builder()
                .rizaNo(entity.getRizaNo())
                .hspTml(hspTml)
                .hspDty(hspDty)
                .build();
    }
}

