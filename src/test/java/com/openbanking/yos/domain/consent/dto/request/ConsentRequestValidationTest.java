package com.openbanking.yos.domain.consent.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConsentRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWithValidRequest() {
        ConsentRequest request = buildValidRequest();

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenKatilimciBlgIsNull() {
        ConsentRequest request = buildValidRequest();
        request.setKatilimciBlg(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("katilimciBlg")));
    }

    @Test
    void shouldFailWhenGkdIsNull() {
        ConsentRequest request = buildValidRequest();
        request.setGkd(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("gkd")));
    }

    @Test
    void shouldFailWhenOdmBsltmIsNull() {
        ConsentRequest request = buildValidRequest();
        request.setOdmBsltm(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("odmBsltm")));
    }

    @Test
    void shouldFailWhenHhsKodIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getKatilimciBlg().setHhsKod("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("hhsKod")));
    }

    @Test
    void shouldFailWhenYosKodIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getKatilimciBlg().setYosKod("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("yosKod")));
    }

    @Test
    void shouldFailWhenOhkTurIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getKmlk().setOhkTur("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ohkTur")));
    }

    @Test
    void shouldFailWhenPrBrmIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setPrBrm("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("prBrm")));
    }

    @Test
    void shouldFailWhenTtrIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ttr")));
    }

    @Test
    void shouldFailWhenTtrHasInvalidFormat() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("abc");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ttr")));
    }

    @Test
    void shouldPassWhenTtrIsInteger() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("1000");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassWhenTtrHasDecimalPart() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("1000.50");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenTtrHasMoreThanFiveDecimalDigits() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getIslTtr().setTtr("100.123456");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("ttr")));
    }

    @Test
    void shouldFailWhenOdmKynkIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getOdmAyr().setOdmKynk("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("odmKynk")));
    }

    @Test
    void shouldFailWhenOdmAmcIsBlank() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getOdmAyr().setOdmAmc("");

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("odmAmc")));
    }

    @Test
    void shouldFailWhenKmlkIsNull() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().setKmlk(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("kmlk")));
    }

    @Test
    void shouldFailWhenIslTtrIsNull() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().setIslTtr(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("islTtr")));
    }

    @Test
    void shouldFailWhenAlcIsNull() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().setAlc(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("alc")));
    }

    @Test
    void shouldFailWhenOdmAyrIsNull() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().setOdmAyr(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().contains("odmAyr")));
    }

    @Test
    void shouldPassWhenGonIsNull() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().setGon(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassWhenRefBlgIsNull() {
        ConsentRequest request = buildValidRequest();
        request.getOdmBsltm().getOdmAyr().setRefBlg(null);

        Set<ConstraintViolation<ConsentRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    private ConsentRequest buildValidRequest() {
        return ConsentRequest.builder()
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
                        .gon(ConsentRequest.Hesap.builder()
                                .unv("Gönderen")
                                .hspNo("TR110006100519786457841326")
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
    }
}
