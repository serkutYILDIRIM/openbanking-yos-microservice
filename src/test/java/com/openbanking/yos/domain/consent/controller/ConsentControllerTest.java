package com.openbanking.yos.domain.consent.controller;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;
import com.openbanking.yos.domain.consent.service.ConsentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsentControllerTest {
   
   @Mock
    private ConsentService consentService;
   
    @InjectMocks
    private ConsentController consentController;

    private ConsentRequest validRequest;
    private ConsentResponse consentResponse;

    @BeforeEach
    void setUp() {
        validRequest = ConsentRequest.builder()
                .katilimciBlg(ConsentRequest.KatilimciBilgisi.builder()
                        .hhsKod("8000")
                        .yosKod("9000")
                        .build())
                .gkd(ConsentRequest.Gkd.builder()
                        .yetYntm("Y")
                        .yonAdr("https://yos.com/redirect")
                        .build())
                .odmBsltm(ConsentRequest.OdemeBaslatma.builder()
                        .kmlk(ConsentRequest.Kimlik.builder()
                                .ohkTur("B")
                                .kmlkTur("K")
                                .kmlkVrs("11111111110")
                                .build())
                        .islTtr(ConsentRequest.Tutar.builder()
                                .prBrm("TRY")
                                .ttr("500.00")
                                .build())
                        .alc(ConsentRequest.Hesap.builder()
                                .unv("Mehmet Yilmaz")
                                .hspNo("TR330006100519786457841326")
                                .build())
                        .odmAyr(ConsentRequest.OdemeAyrintilari.builder()
                                .odmKynk("O")
                                .odmAmc("01")
                                .refBlg("REF456")
                                .build())
                        .build())
                .build();

        consentResponse = ConsentResponse.builder()
                .rzBlg(ConsentResponse.RizaBilgileri.builder()
                        .rizaNo("consent-123")
                        .rizaDrm(ConsentStatus.B)
                        .olusZmn(LocalDateTime.now())
                        .gnclZmn(LocalDateTime.now())
                        .build())
                .katilimciBlg(ConsentResponse.KatilimciBilgisi.builder()
                        .hhsKod("8000")
                        .yosKod("9000")
                        .build())
                .build();
    }

    @Test
    void shouldCreateConsentAndReturn201() {
        when(consentService.createConsent(any(ConsentRequest.class), eq("req-123"), eq("8000"), eq("9000")))
                .thenReturn(consentResponse);

        ResponseEntity<ConsentResponse> result = consentController.createConsent(
                "req-123", "8000", "9000", "sig-abc", validRequest);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("consent-123", result.getBody().getRzBlg().getRizaNo());
        assertEquals(ConsentStatus.B, result.getBody().getRzBlg().getRizaDrm());
        verify(consentService, times(1)).createConsent(any(ConsentRequest.class), eq("req-123"), eq("8000"), eq("9000"));
    }

    @Test
    void shouldGetConsentAndReturn200() {
        when(consentService.getConsent(eq("consent-123"), eq("8000"), eq("9000")))
                .thenReturn(consentResponse);

        ResponseEntity<ConsentResponse> result = consentController.getConsent(
                "consent-123", "req-123", "8000", "9000", "sig-abc");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("consent-123", result.getBody().getRzBlg().getRizaNo());
        assertEquals("8000", result.getBody().getKatilimciBlg().getHhsKod());
        verify(consentService, times(1)).getConsent("consent-123", "8000", "9000");
    }

    @Test
    void shouldThrowExceptionWhenCreateConsentAspspMismatch() {
        when(consentService.createConsent(any(ConsentRequest.class), eq("req-123"), eq("9999"), eq("9000")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidASPSP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentController.createConsent("req-123", "9999", "9000", "sig-abc", validRequest));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreateConsentTppMismatch() {
        when(consentService.createConsent(any(ConsentRequest.class), eq("req-123"), eq("8000"), eq("8888")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidTPP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentController.createConsent("req-123", "8000", "8888", "sig-abc", validRequest));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConsentNotFound() {
        when(consentService.getConsent(eq("consent-999"), eq("8000"), eq("9000")))
                .thenThrow(new OhvpsException("TR.OHVPS.Resource.NotFound"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentController.getConsent("consent-999", "req-123", "8000", "9000", "sig-abc"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetConsentAspspMismatch() {
        when(consentService.getConsent(eq("consent-123"), eq("9999"), eq("9000")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidASPSP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentController.getConsent("consent-123", "req-123", "9999", "9000", "sig-abc"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetConsentTppMismatch() {
        when(consentService.getConsent(eq("consent-123"), eq("8000"), eq("8888")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidTPP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentController.getConsent("consent-123", "req-123", "8000", "8888", "sig-abc"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }
}
