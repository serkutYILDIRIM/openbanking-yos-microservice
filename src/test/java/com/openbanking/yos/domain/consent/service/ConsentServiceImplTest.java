package com.openbanking.yos.domain.consent.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.GkdType;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsentServiceImplTest {

    @Mock
    private ConsentRepository consentRepository;

    @InjectMocks
    private ConsentServiceImpl consentService;

    private ConsentRequest validRequest;
    private ConsentEntity savedConsent;

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
                                .unv("Mehmet Yılmaz")
                                .hspNo("TR330006100519786457841326")
                                .build())
                        .odmAyr(ConsentRequest.OdemeAyrintilari.builder()
                                .odmKynk("O")
                                .odmAmc("01")
                                .refBlg("REF456")
                                .build())
                        .build())
                .build();

        savedConsent = ConsentEntity.builder()
                .id(1L)
                .rizaNo("consent-123")
                .rizaDrm(ConsentStatus.B)
                .hhsKod("8000")
                .yosKod("9000")
                .yetYntm(GkdType.Y)
                .yonAdr("https://yos.com/redirect")
                .yetTmmZmn(LocalDateTime.now().plusMinutes(5))
                .ohkTur("B")
                .kmlkTur("K")
                .kmlkVrs("11111111110")
                .prBrm("TRY")
                .ttr("500.00")
                .alcUnv("Mehmet Yılmaz")
                .alcHspNo("TR330006100519786457841326")
                .odmKynk("O")
                .odmAmc("01")
                .refBlg("REF456")
                .olusZmn(LocalDateTime.now())
                .gnclZmn(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreateConsentSuccessfully() {
        when(consentRepository.findByRizaNo(anyString())).thenReturn(Optional.empty());
        when(consentRepository.save(any(ConsentEntity.class))).thenReturn(savedConsent);

        ConsentResponse response = consentService.createConsent(validRequest, "req-123", "8000", "9000");

        assertNotNull(response);
        assertNotNull(response.getRzBlg());
        assertEquals("consent-123", response.getRzBlg().getRizaNo());
        assertEquals(ConsentStatus.B, response.getRzBlg().getRizaDrm());
        verify(consentRepository, times(1)).save(any(ConsentEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenAspspCodeMismatch() {
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.createConsent(validRequest, "req-123", "7777", "9000"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
        verify(consentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenTppCodeMismatch() {
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.createConsent(validRequest, "req-123", "8000", "8888"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
        verify(consentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenInvalidPaymentSource() {
        validRequest.getOdmBsltm().getOdmAyr().setOdmKynk("H");

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.createConsent(validRequest, "req-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.InvalidFormat", exception.getMessage());
        verify(consentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAyrıkGkdWithoutYonAdr() {
        validRequest.getGkd().setYetYntm("A");
        validRequest.getGkd().setYonAdr(null);

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.createConsent(validRequest, "req-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.InvalidFormat", exception.getMessage());
        verify(consentRepository, never()).save(any());
    }

    @Test
    void shouldReturnExistingConsentWhenIdempotent() {
        when(consentRepository.findByRizaNo("req-123")).thenReturn(Optional.of(savedConsent));

        ConsentResponse response = consentService.createConsent(validRequest, "req-123", "8000", "9000");

        assertNotNull(response);
        assertEquals("consent-123", response.getRzBlg().getRizaNo());
        verify(consentRepository, never()).save(any());
    }

    @Test
    void shouldGetConsentSuccessfully() {
        when(consentRepository.findByRizaNo("consent-123")).thenReturn(Optional.of(savedConsent));

        ConsentResponse response = consentService.getConsent("consent-123", "8000", "9000");

        assertNotNull(response);
        assertEquals("consent-123", response.getRzBlg().getRizaNo());
        verify(consentRepository, times(1)).findByRizaNo("consent-123");
    }

    @Test
    void shouldThrowExceptionWhenConsentNotFound() {
        when(consentRepository.findByRizaNo("consent-123")).thenReturn(Optional.empty());

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.getConsent("consent-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetConsentAspspMismatch() {
        when(consentRepository.findByRizaNo("consent-123")).thenReturn(Optional.of(savedConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.getConsent("consent-123", "7777", "9000"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetConsentTppMismatch() {
        when(consentRepository.findByRizaNo("consent-123")).thenReturn(Optional.of(savedConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.getConsent("consent-123", "8000", "8888"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConsentCancelled() {
        savedConsent.setRizaDrm(ConsentStatus.K);
        when(consentRepository.findByRizaNo("consent-123")).thenReturn(Optional.of(savedConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.getConsent("consent-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.InvalidStatus", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConsentRevoked() {
        savedConsent.setRizaDrm(ConsentStatus.I);
        when(consentRepository.findByRizaNo("consent-123")).thenReturn(Optional.of(savedConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> consentService.getConsent("consent-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.InvalidStatus", exception.getMessage());
    }
}
