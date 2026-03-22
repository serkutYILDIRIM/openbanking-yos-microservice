package com.openbanking.yos.domain.payment.controller;

import com.openbanking.yos.common.enums.PaymentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.payment.dto.request.PaymentRequest;
import com.openbanking.yos.domain.payment.dto.response.PaymentResponse;
import com.openbanking.yos.domain.payment.service.PaymentService;
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
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private PaymentRequest validRequest;
    private PaymentResponse paymentResponse;

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
                                .unv("Ali Yilmaz")
                                .hspNo("TR330006100519786457841326")
                                .build())
                        .odmAyr(PaymentRequest.OdemeAyrintilari.builder()
                                .odmKynk("O")
                                .odmAmc("01")
                                .refBlg("REF123")
                                .odmAcklm("Test odeme")
                                .build())
                        .build())
                .build();

        paymentResponse = PaymentResponse.builder()
                .odmBlg(PaymentResponse.OdemeBilgileri.builder()
                        .odmEmriNo("odm-456")
                        .odmDrm(PaymentStatus.G)
                        .olusZmn(LocalDateTime.now())
                        .gnclZmn(LocalDateTime.now())
                        .build())
                .katilimciBlg(PaymentResponse.KatilimciBilgisi.builder()
                        .hhsKod("8000")
                        .yosKod("9000")
                        .build())
                .build();
    }

    @Test
    void shouldCreatePaymentAndReturn201() {
        when(paymentService.createPayment(any(PaymentRequest.class), eq("req-123"), eq("8000"), eq("9000")))
                .thenReturn(paymentResponse);

        ResponseEntity<PaymentResponse> result = paymentController.createPayment(
                "req-123", "8000", "9000", "sig-abc", validRequest);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("odm-456", result.getBody().getOdmBlg().getOdmEmriNo());
        assertEquals(PaymentStatus.G, result.getBody().getOdmBlg().getOdmDrm());
        verify(paymentService, times(1)).createPayment(any(PaymentRequest.class), eq("req-123"), eq("8000"), eq("9000"));
    }

    @Test
    void shouldGetPaymentAndReturn200() {
        when(paymentService.getPayment(eq("odm-456"), eq("8000"), eq("9000")))
                .thenReturn(paymentResponse);

        ResponseEntity<PaymentResponse> result = paymentController.getPayment(
                "odm-456", "req-123", "8000", "9000", "sig-abc");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("odm-456", result.getBody().getOdmBlg().getOdmEmriNo());
        assertEquals("8000", result.getBody().getKatilimciBlg().getHhsKod());
        verify(paymentService, times(1)).getPayment("odm-456", "8000", "9000");
    }

    @Test
    void shouldThrowExceptionWhenCreatePaymentAspspMismatch() {
        when(paymentService.createPayment(any(PaymentRequest.class), eq("req-123"), eq("9999"), eq("9000")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidASPSP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentController.createPayment("req-123", "9999", "9000", "sig-abc", validRequest));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatePaymentTppMismatch() {
        when(paymentService.createPayment(any(PaymentRequest.class), eq("req-123"), eq("8000"), eq("8888")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidTPP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentController.createPayment("req-123", "8000", "8888", "sig-abc", validRequest));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPaymentNotFound() {
        when(paymentService.getPayment(eq("odm-999"), eq("8000"), eq("9000")))
                .thenThrow(new OhvpsException("TR.OHVPS.Resource.NotFound"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentController.getPayment("odm-999", "req-123", "8000", "9000", "sig-abc"));

        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetPaymentAspspMismatch() {
        when(paymentService.getPayment(eq("odm-456"), eq("9999"), eq("9000")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidASPSP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentController.getPayment("odm-456", "req-123", "9999", "9000", "sig-abc"));

        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenGetPaymentTppMismatch() {
        when(paymentService.getPayment(eq("odm-456"), eq("8000"), eq("8888")))
                .thenThrow(new OhvpsException("TR.OHVPS.Connection.InvalidTPP"));

        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> paymentController.getPayment("odm-456", "req-123", "8000", "8888", "sig-abc"));

        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }
}
