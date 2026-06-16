package com.openbanking.yos.domain.card.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CardEntityAnnotationTest {

    @Test
    void shouldHaveEntityAnnotation() {
        assertTrue(CardEntity.class.isAnnotationPresent(Entity.class));
    }

    @Test
    void shouldHaveTableAnnotationWithCorrectName() {
        Table table = CardEntity.class.getAnnotation(Table.class);

        assertNotNull(table);
        assertEquals("ohvps_kart", table.name());
    }

    @Test
    void shouldHaveIdAnnotationOnIdField() throws NoSuchFieldException {
        Field idField = CardEntity.class.getDeclaredField("id");

        assertTrue(idField.isAnnotationPresent(Id.class));
    }

    @Test
    void shouldHaveGeneratedValueWithIdentityStrategy() throws NoSuchFieldException {
        Field idField = CardEntity.class.getDeclaredField("id");
        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);

        assertNotNull(generatedValue);
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void shouldHaveNotNullConstraintOnRizaNo() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("rizaNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
    }

    @Test
    void shouldHaveUniqueAndNotNullConstraintOnKartRef() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartRef");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertTrue(column.unique());
        assertFalse(column.nullable());
        assertEquals(40, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartNo() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(16, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnAsilKartNo() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("asilKartNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(16, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartTipi() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartTipi");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnAltKartTipi() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("altKartTipi");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartFormu() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartFormu");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartTuru() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartTuru");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartStatu() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartStatu");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartSahibi() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartSahibi");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnKartTicariUnvan() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartTicariUnvan");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartUrunAdi() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartUrunAdi");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnKartRumuz() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartRumuz");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnKartSema() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("kartSema");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHhsKod() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("hhsKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnYosKod() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("yosKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCreationTimestampOnOlusZmn() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("olusZmn");

        assertTrue(field.isAnnotationPresent(CreationTimestamp.class));
    }

    @Test
    void shouldHaveUpdateTimestampOnGnclZmn() throws NoSuchFieldException {
        Field field = CardEntity.class.getDeclaredField("gnclZmn");

        assertTrue(field.isAnnotationPresent(UpdateTimestamp.class));
    }

    @Test
    void shouldHaveExpectedNumberOfDeclaredFields() {
        Field[] fields = CardEntity.class.getDeclaredFields();

        assertEquals(19, fields.length);
    }
}
