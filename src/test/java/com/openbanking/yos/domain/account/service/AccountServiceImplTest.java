package com.openbanking.yos.domain.account.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.account.dto.response.AccountListResponse;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;
import com.openbanking.yos.domain.account.entity.AccountEntity;
import com.openbanking.yos.domain.account.repository.AccountRepository;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ConsentRepository consentRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private ConsentEntity activeConsent;
    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        activeConsent = ConsentEntity.builder()
                .id(1L)
                .rizaNo("riza-123")
                .rizaDrm(ConsentStatus.E)
                .hhsKod("8000")
                .yosKod("9000")
                .olusZmn(LocalDateTime.now())
                .gnclZmn(LocalDateTime.now())
                .build();

        accountEntity = AccountEntity.builder()
                .id(1L)
                .hspRef("hsp-ref-001")
                .rizaNo("riza-123")
                .hspNo("TR330006100519786457841326")
                .hspShb("12345678")
                .subeAdi("Merkez Şube")
                .kisaAd("Vadesiz TRY")
                .prBrm("TRY")
                .hspTur("B")
                .hspTip("VADESIZ")
                .hspUrunAdi("Bireysel Vadesiz Hesap")
                .hspDrm("AKTIF")
                .hspAclsTrh(LocalDateTime.of(2020, 1, 15, 0, 0))
                .olusZmn(LocalDateTime.now())
                .gnclZmn(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldGetAccountsSuccessfully() {
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));
        when(accountRepository.findByRizaNo("riza-123")).thenReturn(List.of(accountEntity));

        AccountListResponse response = accountService.getAccounts("8000", "9000", "riza-123");

        assertNotNull(response);
        assertNotNull(response.getHesaplar());
        assertEquals(1, response.getHesaplar().size());
        assertEquals("riza-123", response.getHesaplar().get(0).getRizaNo());
        verify(consentRepository, times(1)).findByRizaNo("riza-123");
        verify(accountRepository, times(1)).findByRizaNo("riza-123");
    }

    @Test
    void shouldGetAccountsWithConsentStatusK() {
        activeConsent.setRizaDrm(ConsentStatus.K);
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));
        when(accountRepository.findByRizaNo("riza-123")).thenReturn(List.of(accountEntity));

        AccountListResponse response = accountService.getAccounts("8000", "9000", "riza-123");

        assertNotNull(response);
        assertEquals(1, response.getHesaplar().size());
    }

    @Test
    void shouldThrowExceptionWhenConsentNotFoundForGetAccounts() {
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.empty());

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccounts("8000", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
        verify(accountRepository, never()).findByRizaNo(any());
    }

    @Test
    void shouldThrowExceptionWhenAspspCodeMismatchForGetAccounts() {
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccounts("9999", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
        verify(accountRepository, never()).findByRizaNo(any());
    }

    @Test
    void shouldThrowExceptionWhenTppCodeMismatchForGetAccounts() {
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccounts("8000", "8888", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
        verify(accountRepository, never()).findByRizaNo(any());
    }

    @Test
    void shouldThrowExceptionWhenConsentStatusInvalidForGetAccounts() {
        activeConsent.setRizaDrm(ConsentStatus.B);
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccounts("8000", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Resource.ConsentMismatch", exception.getMessage());
        verify(accountRepository, never()).findByRizaNo(any());
    }

    @Test
    void shouldGetAccountSuccessfully() {
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        AccountResponse response = accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123");

        assertNotNull(response);
        assertEquals("riza-123", response.getRizaNo());
        assertNotNull(response.getHspTml());
        assertEquals("hsp-ref-001", response.getHspTml().getHspRef());
        verify(accountRepository, times(1)).findByHspRef("hsp-ref-001");
        verify(consentRepository, times(1)).findByRizaNo("riza-123");
    }

    @Test
    void shouldReturnHspDtyWhenHspAclsTrhExists() {
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        AccountResponse response = accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123");

        assertNotNull(response.getHspDty());
        assertEquals(accountEntity.getHspAclsTrh(), response.getHspDty().getHspAclsTrh());
    }

    @Test
    void shouldReturnNullHspDtyWhenHspAclsTrhIsNull() {
        accountEntity.setHspAclsTrh(null);
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        AccountResponse response = accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123");

        assertNull(response.getHspDty());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.empty());

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
        verify(consentRepository, never()).findByRizaNo(any());
    }

    @Test
    void shouldThrowExceptionWhenConsentNotFoundForGetAccount() {
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.empty());

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAspspCodeMismatchForGetAccount() {
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccount("hsp-ref-001", "9999", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTppCodeMismatchForGetAccount() {
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccount("hsp-ref-001", "8000", "8888", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConsentStatusInvalidForGetAccount() {
        activeConsent.setRizaDrm(ConsentStatus.Y);
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Resource.ConsentMismatch", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRizaNoMismatchForGetAccount() {
        accountEntity.setRizaNo("farkli-riza");
        when(accountRepository.findByHspRef("hsp-ref-001")).thenReturn(Optional.of(accountEntity));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountService.getAccount("hsp-ref-001", "8000", "9000", "riza-123"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }
}
