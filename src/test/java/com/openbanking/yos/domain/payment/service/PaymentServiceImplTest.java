package com.openbanking.yos.domain.payment.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.GkdType;
import com.openbanking.yos.common.enums.PaymentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import com.openbanking.yos.domain.payment.dto.request.PaymentRequest;
import com.openbanking.yos.domain.payment.dto.response.PaymentResponse;
import com.openbanking.yos.domain.payment.entity.PaymentEntity;
import com.openbanking.yos.domain.payment.repository.PaymentRepository;
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
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ConsentRepository consentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private PaymentRequest validRequest;
    private ConsentEntity activeConsent;
    private PaymentEntity savedPayment;

    @BeforeEach
    void setUp() {
        validRequest = PaymentRequest.builder()
                .rzBlg(PaymentRequest.RizaBilgileri.builder()
                        .rizaNo("riza-123")
                        .build())
                .katilimciBlg(PaymentRequest.KatilimciBilgisi.builder()
                        .hhsKod("8000")
                        .yosKod("9000")
                        .build())
                .odmBsltm(PaymentRequest.OdemeBaslatma.builder()
                        .kmlk(PaymentRequest.Kimlik.builder()
                                .ohkTur("B")
                                .kmlkTur("K")
                                .kmlkVrs("11111111110")
                                .build())
                        .islTtr(PaymentRequest.Tutar.builder()
                                .prBrm("TRY")
                                .ttr("100.00")
                                .build())
                        .alc(PaymentRequest.Hesap.builder()
                                .unv("Ali Yılmaz")
                                .hspNo("TR330006100519786457841326")
                                .build())
                        .odmAyr(PaymentRequest.OdemeAyrintilari.builder()
                                .odmKynk("O")
                                .odmAmc("01")
                                .refBlg("REF123")
                                .odmAcklm("Test ödeme")
                                .build())
                        .build())
                .build();

        activeConsent = ConsentEntity.builder()
                .id(1L)
                .rizaNo("riza-123")
                .rizaDrm(ConsentStatus.Y)
                .hhsKod("8000")
                .yosKod("9000")
                .yetYntm(GkdType.Y)
                .yonAdr("https://redirect.url")
                .hhsYonAdr("https://hhs.url")
                .olusZmn(LocalDateTime.now())
                .gnclZmn(LocalDateTime.now())
                .build();

        savedPayment = PaymentEntity.builder()
                .id(1L)
                .odmEmriNo("odm-456")
                .rizaNo("riza-123")
                .odmDrm(PaymentStatus.G)
                .hhsKod("8000")
                .yosKod("9000")
                .yetYntm(GkdType.Y)
                .yonAdr("https://redirect.url")
                .hhsYonAdr("https://hhs.url")
                .ohkTur("B")
                .kmlkTur("K")
                .kmlkVrs("11111111110")
                .prBrm("TRY")
                .ttr("100.00")
                .alcUnv("Ali Yılmaz")
                .alcHspNo("TR330006100519786457841326")
                .odmKynk("O")
                .odmAmc("01")
                .refBlg("REF123")
                .odmAcklm("Test ödeme")
                .olusZmn(LocalDateTime.now())
                .gnclZmn(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreatePaymentSuccessfully() {
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));
        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(savedPayment);

        PaymentResponse response = paymentService.createPayment(validRequest, "req-123", "8000", "9000");

        assertNotNull(response);
        assertNotNull(response.getOdmBlg());
        assertEquals("odm-456", response.getOdmBlg().getOdmEmriNo());
        assertEquals(PaymentStatus.G, response.getOdmBlg().getOdmDrm());
        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
        verify(consentRepository, times(1)).save(activeConsent);
        assertEquals(ConsentStatus.E, activeConsent.getRizaDrm());
    }

    @Test
    void shouldThrowExceptionWhenAspspCodeMismatch() {
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.createPayment(validRequest, "req-123", "9999", "9000"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenTppCodeMismatch() {
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.createPayment(validRequest, "req-123", "8000", "8888"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenConsentNotFound() {
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.empty());

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.createPayment(validRequest, "req-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenConsentNotAuthorized() {
        activeConsent.setRizaDrm(ConsentStatus.B);
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.createPayment(validRequest, "req-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenConsentAspspMismatch() {
        activeConsent.setHhsKod("7777");
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.createPayment(validRequest, "req-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenConsentTppMismatch() {
        activeConsent.setYosKod("6666");
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.createPayment(validRequest, "req-123", "8000", "9000"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldGetPaymentSuccessfully() {
        when(paymentRepository.findByOdmEmriNo("odm-456")).thenReturn(Optional.of(savedPayment));
        when(consentRepository.findByRizaNo("riza-123")).thenReturn(Optional.of(activeConsent));

        PaymentResponse response = paymentService.getPayment("odm-456", "8000", "9000");

        assertNotNull(response);
        assertEquals("odm-456", response.getOdmBlg().getOdmEmriNo());
        verify(paymentRepository, times(1)).findByOdmEmriNo("odm-456");
    }

    @Test
    void shouldThrowExceptionWhenPaymentNotFound() {
        when(paymentRepository.findByOdmEmriNo("odm-456")).thenReturn(Optional.empty());

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.getPayment("odm-456", "8000", "9000"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetPaymentAspspMismatch() {
        when(paymentRepository.findByOdmEmriNo("odm-456")).thenReturn(Optional.of(savedPayment));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.getPayment("odm-456", "9999", "9000"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetPaymentTppMismatch() {
        when(paymentRepository.findByOdmEmriNo("odm-456")).thenReturn(Optional.of(savedPayment));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentService.getPayment("odm-456", "8000", "8888"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }
}
