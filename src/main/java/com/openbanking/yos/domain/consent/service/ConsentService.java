package com.openbanking.yos.domain.consent.service;

import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;

public interface ConsentService {

    ConsentResponse createConsent(ConsentRequest request, String xRequestId, String xAspspCode, String xTppCode);

    ConsentResponse getConsent(String rizaNo, String xAspspCode, String xTppCode);
}
