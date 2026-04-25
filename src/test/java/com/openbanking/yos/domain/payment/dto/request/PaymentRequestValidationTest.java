package com.openbanking.yos.domain.payment.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWithValidRequest() {
        PaymentRequest request = buildValidRequest();

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenRzBlgIsNull() {
        PaymentRequest request = buildValidRequest();
        request.setRzBlg(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("rzBlg")));
    }

    @Test
    void shouldFailWhenKatilimciBlgIsNull() {
        PaymentRequest request = buildValidRequest();
        request.setKatilimciBlg(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("katilimciBlg")));
    }

    @Test
    void shouldFailWhenOdmBsltmIsNull() {
        PaymentRequest request = buildValidRequest();
        request.setOdmBsltm(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("odmBsltm")));
    }

    @Test
    void shouldFailWhenRizaNoIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getRzBlg().setRizaNo("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("rizaNo")));
    }

    @Test
    void shouldFailWhenHhsKodIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getKatilimciBlg().setHhsKod("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("hhsKod")));
    }

    @Test
    void shouldFailWhenYosKodIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getKatilimciBlg().setYosKod("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("yosKod")));
    }

    @Test
    void shouldFailWhenOhkTurIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getKmlk().setOhkTur("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ohkTur")));
    }

    @Test
    void shouldFailWhenPrBrmIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setPrBrm("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("prBrm")));
    }

    @Test
    void shouldFailWhenTtrIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ttr")));
    }

    @Test
    void shouldFailWhenTtrHasInvalidFormat() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("abc");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ttr")));
    }

    @Test
    void shouldPassWhenTtrIsInteger() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("1000");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassWhenTtrHasDecimalPart() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("1000.50");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenTtrHasMoreThanFiveDecimalDigits() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("100.123456");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ttr")));
    }

    @Test
    void shouldFailWhenOdmKynkIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getOdmAyr().setOdmKynk("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("odmKynk")));
    }

    @Test
    void shouldFailWhenOdmAmcIsBlank() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getOdmAyr().setOdmAmc("");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("odmAmc")));
    }

    @Test
    void shouldFailWhenKmlkIsNull() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().setKmlk(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("kmlk")));
    }

    @Test
    void shouldFailWhenIslTtrIsNull() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().setIslTtr(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("islTtr")));
    }

    @Test
    void shouldFailWhenAlcIsNull() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().setAlc(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("alc")));
    }

    @Test
    void shouldFailWhenOdmAyrIsNull() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().setOdmAyr(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("odmAyr")));
    }

    @Test
    void shouldPassWhenGonIsNull() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().setGon(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassWhenRefBlgIsNull() {
        PaymentRequest request = buildValidRequest();
        request.getOdmBsltm().getOdmAyr().setRefBlg(null);

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    private PaymentRequest buildValidRequest() {
        return PaymentRequest.builder()
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
                                .ttr("500.00")
                                .build())
                        .gon(PaymentRequest.Hesap.builder()
                                .unv("Gönderen")
                                .hspNo("TR110006100519786457841326")
                                .build())
                        .alc(PaymentRequest.Hesap.builder()
                                .unv("Mehmet Yilmaz")
                                .hspNo("TR330006100519786457841326")
                                .build())
                        .odmAyr(PaymentRequest.OdemeAyrintilari.builder()
                                .odmKynk("O")
                                .odmAmc("01")
                                .refBlg("REF456")
                                .build())
                        .build())
                .build();
    }
}
