package com.openbanking.yos.domain.consent.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ConsentEntityAnnotationTest {

    @Test
    void shouldHaveEntityAnnotation() {
        assertTrue(ConsentEntity.class.isAnnotationPresent(Entity.class));
    }

    @Test
    void shouldHaveTableAnnotationWithCorrectName() {
        Table table = ConsentEntity.class.getAnnotation(Table.class);

        assertNotNull(table);
        assertEquals("ohvps_rizasi", table.name());
    }

    @Test
    void shouldHaveIdAnnotationOnIdField() throws NoSuchFieldException {
        Field idField = ConsentEntity.class.getDeclaredField("id");

        assertTrue(idField.isAnnotationPresent(Id.class));
    }

    @Test
    void shouldHaveGeneratedValueWithIdentityStrategy() throws NoSuchFieldException {
        Field idField = ConsentEntity.class.getDeclaredField("id");
        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);

        assertNotNull(generatedValue);
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void shouldHaveUniqueAndNotNullConstraintOnRizaNo() throws NoSuchFieldException {
        Field rizaNoField = ConsentEntity.class.getDeclaredField("rizaNo");
        Column column = rizaNoField.getAnnotation(Column.class);

        assertNotNull(column);
        assertTrue(column.unique());
        assertFalse(column.nullable());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHhsKod() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("hhsKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnYosKod() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("yosKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveEnumeratedStringOnRizaDrm() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("rizaDrm");
        Enumerated enumerated = field.getAnnotation(Enumerated.class);

        assertNotNull(enumerated);
        assertEquals(EnumType.STRING, enumerated.value());
    }

    @Test
    void shouldHaveEnumeratedStringOnYetYntm() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("yetYntm");
        Enumerated enumerated = field.getAnnotation(Enumerated.class);

        assertNotNull(enumerated);
        assertEquals(EnumType.STRING, enumerated.value());
    }

    @Test
    void shouldHaveCreationTimestampOnOlusZmn() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("olusZmn");

        assertTrue(field.isAnnotationPresent(CreationTimestamp.class));
    }

    @Test
    void shouldHaveUpdateTimestampOnGnclZmn() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("gnclZmn");

        assertTrue(field.isAnnotationPresent(UpdateTimestamp.class));
    }

    @Test
    void shouldHaveNotNullConstraintOnAlcUnv() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("alcUnv");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnAlcHspNo() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("alcHspNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(26, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnOdmKynk() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("odmKynk");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnOdmAmc() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("odmAmc");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(2, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnYonAdr() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("yonAdr");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(1024, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHhsYonAdr() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("hhsYonAdr");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(1024, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnRefBlg() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("refBlg");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(140, column.length());
    }

    @Test
    void shouldAllowNullOnGonUnv() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("gonUnv");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertTrue(column.nullable());
    }

    @Test
    void shouldAllowNullOnGonHspNo() throws NoSuchFieldException {
        Field field = ConsentEntity.class.getDeclaredField("gonHspNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertTrue(column.nullable());
    }

    @Test
    void shouldHaveExpectedNumberOfDeclaredFields() {
        Field[] fields = ConsentEntity.class.getDeclaredFields();

        assertEquals(24, fields.length);
    }
}
