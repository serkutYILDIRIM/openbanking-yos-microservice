package com.openbanking.yos.domain.payment.controller;

import com.openbanking.yos.domain.payment.dto.request.PaymentRequest;
import com.openbanking.yos.domain.payment.dto.response.PaymentResponse;
import com.openbanking.yos.domain.payment.service.PaymentService;
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
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/odeme-emri")
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature,
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response = paymentService.createPayment(request, xRequestId, xAspspCode, xTppCode);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/odeme-emri/{odmEmriNo}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable String odmEmriNo,
            @RequestHeader("x-request-id")    String xRequestId,
            @RequestHeader("x-aspsp-code")    String xAspspCode,
            @RequestHeader("x-tpp-code")      String xTppCode,
            @RequestHeader("x-jws-signature") String xJwsSignature) {

        PaymentResponse response = paymentService.getPayment(odmEmriNo, xAspspCode, xTppCode);
        return ResponseEntity.ok(response);
    }
}

