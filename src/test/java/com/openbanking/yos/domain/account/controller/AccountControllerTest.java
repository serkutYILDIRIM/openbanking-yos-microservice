package com.openbanking.yos.domain.account.controller;

import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.account.dto.response.AccountListResponse;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;
import com.openbanking.yos.domain.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private AccountListResponse accountListResponse;
    private AccountResponse accountResponse;

    @BeforeEach
    void setUp() {
        AccountResponse.HesapTemel hesapTemel = AccountResponse.HesapTemel.builder()
                .hspRef("hsp-ref-001")
                .hspTur("B")
                .hspTip("VADESIZ")
                .hspNo("TR330006100519786457841326")
                .hspShb("12345678")
                .subeAdi("Merkez Şube")
                .kisaAd("Vadesiz TRY")
                .prBrm("TRY")
                .hspUrunAdi("Bireysel Vadesiz Hesap")
                .hspDrm("AKTIF")
                .build();

        AccountResponse.HesapDetay hesapDetay = AccountResponse.HesapDetay.builder()
                .hspAclsTrh(LocalDateTime.of(2020, 1, 15, 0, 0))
                .build();

        accountResponse = AccountResponse.builder()
                .rizaNo("riza-123")
                .hspTml(hesapTemel)
                .hspDty(hesapDetay)
                .build();

        accountListResponse = AccountListResponse.builder()
                .hesaplar(List.of(accountResponse))
                .build();
    }

    @Test
    void shouldGetAccountsAndReturn200() {
        when(accountService.getAccounts(eq("8000"), eq("9000"), eq("riza-123")))
                .thenReturn(accountListResponse);

        ResponseEntity<AccountListResponse> result = accountController.getAccounts(
                "req-123", "8000", "9000", "sig-abc", "riza-123");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getHesaplar().size());
        assertEquals("riza-123", result.getBody().getHesaplar().get(0).getRizaNo());
        verify(accountService, times(1)).getAccounts("8000", "9000", "riza-123");
    }

    @Test
    void shouldGetAccountAndReturn200() {
        when(accountService.getAccount(eq("hsp-ref-001"), eq("8000"), eq("9000"), eq("riza-123")))
                .thenReturn(accountResponse);

        ResponseEntity<AccountResponse> result = accountController.getAccount(
                "hsp-ref-001", "req-123", "8000", "9000", "sig-abc", "riza-123");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("riza-123", result.getBody().getRizaNo());
        assertEquals("hsp-ref-001", result.getBody().getHspTml().getHspRef());
        verify(accountService, times(1)).getAccount("hsp-ref-001", "8000", "9000", "riza-123");
    }

    @Test
    void shouldThrowExceptionWhenGetAccountsConsentNotFound() {
        when(accountService.getAccounts(eq("8000"), eq("9000"), eq("riza-999")))
                .thenThrow(new OhvpsException("TR.OHVPS.Resource.NotFound"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountController.getAccounts("req-123", "8000", "9000", "sig-abc", "riza-999"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetAccountsAspspMismatch() {
        when(accountService.getAccounts(eq("9999"), eq("9000"), eq("riza-123")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidASPSP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountController.getAccounts("req-123", "9999", "9000", "sig-abc", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetAccountsTppMismatch() {
        when(accountService.getAccounts(eq("8000"), eq("8888"), eq("riza-123")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidTPP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountController.getAccounts("req-123", "8000", "8888", "sig-abc", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountService.getAccount(eq("hsp-ref-999"), eq("8000"), eq("9000"), eq("riza-123")))
                .thenThrow(new OhvpsException("TR.OHVPS.Resource.NotFound"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountController.getAccount("hsp-ref-999", "req-123", "8000", "9000", "sig-abc", "riza-123"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetAccountAspspMismatch() {
        when(accountService.getAccount(eq("hsp-ref-001"), eq("9999"), eq("9000"), eq("riza-123")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidASPSP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountController.getAccount("hsp-ref-001", "req-123", "9999", "9000", "sig-abc", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetAccountTppMismatch() {
        when(accountService.getAccount(eq("hsp-ref-001"), eq("8000"), eq("8888"), eq("riza-123")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidTPP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> accountController.getAccount("hsp-ref-001", "req-123", "8000", "8888", "sig-abc", "riza-123"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }
}
