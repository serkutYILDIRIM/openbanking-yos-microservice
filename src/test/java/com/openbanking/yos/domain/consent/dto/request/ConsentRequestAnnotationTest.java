package com.openbanking.yos.domain.consent.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ConsentRequestAnnotationTest {

    @Test
    void shouldHaveNotNullAndValidOnKatilimciBlg() throws NoSuchFieldException {
        Field field = ConsentRequest.class.getDeclaredField("katilimciBlg");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveJsonPropertyOnKatilimciBlg() throws NoSuchFieldException {
        Field field = ConsentRequest.class.getDeclaredField("katilimciBlg");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("katilimciBlg", jsonProperty.value());
    }

    @Test
    void shouldHaveNotNullAndValidOnGkd() throws NoSuchFieldException {
        Field field = ConsentRequest.class.getDeclaredField("gkd");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveJsonPropertyOnGkd() throws NoSuchFieldException {
        Field field = ConsentRequest.class.getDeclaredField("gkd");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("gkd", jsonProperty.value());
    }

    @Test
    void shouldHaveNotNullAndValidOnOdmBsltm() throws NoSuchFieldException {
        Field field = ConsentRequest.class.getDeclaredField("odmBsltm");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveJsonPropertyOnOdmBsltm() throws NoSuchFieldException {
        Field field = ConsentRequest.class.getDeclaredField("odmBsltm");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("odmBsltm", jsonProperty.value());
    }

    @Test
    void shouldHaveNotBlankOnHhsKod() throws NoSuchFieldException {
        Field field = ConsentRequest.KatilimciBilgisi.class.getDeclaredField("hhsKod");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveJsonPropertyOnHhsKod() throws NoSuchFieldException {
        Field field = ConsentRequest.KatilimciBilgisi.class.getDeclaredField("hhsKod");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("hhsKod", jsonProperty.value());
    }

    @Test
    void shouldHaveNotBlankOnYosKod() throws NoSuchFieldException {
        Field field = ConsentRequest.KatilimciBilgisi.class.getDeclaredField("yosKod");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveJsonPropertyOnYosKod() throws NoSuchFieldException {
        Field field = ConsentRequest.KatilimciBilgisi.class.getDeclaredField("yosKod");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("yosKod", jsonProperty.value());
    }

    @Test
    void shouldHaveJsonPropertyOnYetYntm() throws NoSuchFieldException {
        Field field = ConsentRequest.Gkd.class.getDeclaredField("yetYntm");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("yetYntm", jsonProperty.value());
    }

    @Test
    void shouldHaveJsonPropertyOnYonAdr() throws NoSuchFieldException {
        Field field = ConsentRequest.Gkd.class.getDeclaredField("yonAdr");
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

        assertNotNull(jsonProperty);
        assertEquals("yonAdr", jsonProperty.value());
    }

    @Test
    void shouldNotHaveNotBlankOnYetYntm() throws NoSuchFieldException {
        Field field = ConsentRequest.Gkd.class.getDeclaredField("yetYntm");

        assertFalse(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveNotBlankOnOhkTur() throws NoSuchFieldException {
        Field field = ConsentRequest.Kimlik.class.getDeclaredField("ohkTur");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldNotHaveNotBlankOnKmlkTur() throws NoSuchFieldException {
        Field field = ConsentRequest.Kimlik.class.getDeclaredField("kmlkTur");

        assertFalse(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldNotHaveNotBlankOnKmlkVrs() throws NoSuchFieldException {
        Field field = ConsentRequest.Kimlik.class.getDeclaredField("kmlkVrs");

        assertFalse(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveNotBlankOnPrBrm() throws NoSuchFieldException {
        Field field = ConsentRequest.Tutar.class.getDeclaredField("prBrm");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveNotBlankAndPatternOnTtr() throws NoSuchFieldException {
        Field field = ConsentRequest.Tutar.class.getDeclaredField("ttr");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
        assertTrue(field.isAnnotationPresent(Pattern.class));
    }

    @Test
    void shouldHaveCorrectPatternOnTtr() throws NoSuchFieldException {
        Field field = ConsentRequest.Tutar.class.getDeclaredField("ttr");
        Pattern pattern = field.getAnnotation(Pattern.class);

        assertNotNull(pattern);
        assertEquals("^\\d{1,18}$|^\\d{1,18}\\.\\d{1,5}$", pattern.regexp());
    }

    @Test
    void shouldNotHaveValidationAnnotationsOnHesapFields() throws NoSuchFieldException {
        Field unvField = ConsentRequest.Hesap.class.getDeclaredField("unv");
        Field hspNoField = ConsentRequest.Hesap.class.getDeclaredField("hspNo");

        assertFalse(unvField.isAnnotationPresent(NotBlank.class));
        assertFalse(hspNoField.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveNotBlankOnOdmKynk() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeAyrintilari.class.getDeclaredField("odmKynk");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveNotBlankOnOdmAmc() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeAyrintilari.class.getDeclaredField("odmAmc");

        assertTrue(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldNotHaveNotBlankOnRefBlg() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeAyrintilari.class.getDeclaredField("refBlg");

        assertFalse(field.isAnnotationPresent(NotBlank.class));
    }

    @Test
    void shouldHaveNotNullAndValidOnKmlkInOdemeBaslatma() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeBaslatma.class.getDeclaredField("kmlk");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveNotNullAndValidOnIslTtrInOdemeBaslatma() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeBaslatma.class.getDeclaredField("islTtr");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveValidButNotNotNullOnGonInOdemeBaslatma() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeBaslatma.class.getDeclaredField("gon");

        assertTrue(field.isAnnotationPresent(Valid.class));
        assertFalse(field.isAnnotationPresent(NotNull.class));
    }

    @Test
    void shouldHaveNotNullAndValidOnAlcInOdemeBaslatma() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeBaslatma.class.getDeclaredField("alc");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveNotNullAndValidOnOdmAyrInOdemeBaslatma() throws NoSuchFieldException {
        Field field = ConsentRequest.OdemeBaslatma.class.getDeclaredField("odmAyr");

        assertTrue(field.isAnnotationPresent(NotNull.class));
        assertTrue(field.isAnnotationPresent(Valid.class));
    }

    @Test
    void shouldHaveExpectedNumberOfFieldsInConsentRequest() {
        Field[] fields = ConsentRequest.class.getDeclaredFields();

        assertEquals(3, fields.length);
    }

    @Test
    void shouldHaveExpectedNumberOfFieldsInKatilimciBilgisi() {
        Field[] fields = ConsentRequest.KatilimciBilgisi.class.getDeclaredFields();

        assertEquals(2, fields.length);
    }

    @Test
    void shouldHaveExpectedNumberOfFieldsInOdemeBaslatma() {
        Field[] fields = ConsentRequest.OdemeBaslatma.class.getDeclaredFields();

        assertEquals(5, fields.length);
    }
}
