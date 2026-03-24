package com.openbanking.yos.domain.account.controller;

import com.openbanking.yos.domain.account.dto.response.AccountListResponse;
import com.openbanking.yos.domain.account.dto.response.AccountResponse;
import com.openbanking.yos.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ohvps/hbh/s2.0")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/hesaplar")
    public ResponseEntity<AccountListResponse> getAccounts(
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature,
            @RequestHeader("x-riza-no")       String xRizaNo) {

        AccountListResponse response = accountService.getAccounts(xAspspCode, xTppCode, xRizaNo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hesaplar/{hspRef}")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable String hspRef,
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature,
            @RequestHeader("x-riza-no")       String xRizaNo) {

        AccountResponse response = accountService.getAccount(hspRef, xAspspCode, xTppCode, xRizaNo);
        return ResponseEntity.ok(response);
    }
}

