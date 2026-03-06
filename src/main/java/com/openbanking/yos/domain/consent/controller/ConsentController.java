package com.openbanking.yos.domain.consent.controller;

import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;
import com.openbanking.yos.domain.consent.service.ConsentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ohvps/obh/s2.0")
@RequiredArgsConstructor
public class ConsentController {

    private final ConsentService consentService;

    @PostMapping("/odeme-emri-rizasi")
    public ResponseEntity<ConsentResponse> createConsent(
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature,
            @Valid @RequestBody ConsentRequest request) {

        ConsentResponse response = consentService.createConsent(request, xRequestId, xAspspCode, xTppCode);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/odeme-emri-rizasi/{rizaNo}")
    public ResponseEntity<ConsentResponse> getConsent(
            @PathVariable String rizaNo,
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature) {

        ConsentResponse response = consentService.getConsent(rizaNo, xAspspCode, xTppCode);
        return ResponseEntity.ok(response);
    }
}
