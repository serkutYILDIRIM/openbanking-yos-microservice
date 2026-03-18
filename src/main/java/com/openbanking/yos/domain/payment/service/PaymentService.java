package com.openbanking.yos.domain.payment.service;

import com.openbanking.yos.domain.payment.dto.request.PaymentRequest;
import com.openbanking.yos.domain.payment.dto.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request, String xRequestId, String xAspspCode, String xTppCode);
}

