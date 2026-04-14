package com.openbanking.yos.domain.card.controller;

import com.openbanking.yos.domain.card.dto.response.CardListResponse;
import com.openbanking.yos.domain.card.dto.response.CardResponse;
import com.openbanking.yos.domain.card.service.CardService;
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
public class CardController {

    private final CardService cardService;

    @GetMapping("/kartlar")
    public ResponseEntity<CardListResponse> getCards(
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature,
            @RequestHeader("x-riza-no")       String xRizaNo) {

        CardListResponse response = cardService.getCards(xAspspCode, xTppCode, xRizaNo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/kartlar/{kartRef}")
    public ResponseEntity<CardResponse> getCard(
            @PathVariable String kartRef,
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature,
            @RequestHeader("x-riza-no")       String xRizaNo) {

        CardResponse response = cardService.getCard(kartRef, xAspspCode, xTppCode, xRizaNo);
        return ResponseEntity.ok(response);
    }
}

