package com.openbanking.yos.domain.payment.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityAnnotationTest {

    @Test
    void shouldHaveEntityAnnotation() {
        assertTrue(PaymentEntity.class.isAnnotationPresent(Entity.class));
    }

    @Test
    void shouldHaveTableAnnotationWithCorrectName() {
        Table table = PaymentEntity.class.getAnnotation(Table.class);

        assertNotNull(table);
        assertEquals("ohvps_odeme", table.name());
    }

    @Test
    void shouldHaveIdAnnotationOnIdField() throws NoSuchFieldException {
        Field idField = PaymentEntity.class.getDeclaredField("id");

        assertTrue(idField.isAnnotationPresent(Id.class));
    }

    @Test
    void shouldHaveGeneratedValueWithIdentityStrategy() throws NoSuchFieldException {
        Field idField = PaymentEntity.class.getDeclaredField("id");
        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);

        assertNotNull(generatedValue);
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void shouldHaveUniqueAndNotNullConstraintOnOdmEmriNo() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("odmEmriNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertTrue(column.unique());
        assertFalse(column.nullable());
    }

    @Test
    void shouldHaveNotNullConstraintOnRizaNo() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("rizaNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
    }

    @Test
    void shouldHaveEnumeratedStringOnOdmDrm() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("odmDrm");
        Enumerated enumerated = field.getAnnotation(Enumerated.class);

        assertNotNull(enumerated);
        assertEquals(EnumType.STRING, enumerated.value());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHhsKod() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("hhsKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnYosKod() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("yosKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCreationTimestampOnOlusZmn() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("olusZmn");

        assertTrue(field.isAnnotationPresent(CreationTimestamp.class));
    }

    @Test
    void shouldHaveUpdateTimestampOnGnclZmn() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("gnclZmn");

        assertTrue(field.isAnnotationPresent(UpdateTimestamp.class));
    }

    @Test
    void shouldHaveEnumeratedStringOnYetYntm() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("yetYntm");
        Enumerated enumerated = field.getAnnotation(Enumerated.class);

        assertNotNull(enumerated);
        assertEquals(EnumType.STRING, enumerated.value());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnYonAdr() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("yonAdr");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(1024, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHhsYonAdr() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("hhsYonAdr");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(1024, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnOhkTur() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("ohkTur");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnKmlkTur() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("kmlkTur");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnKmlkVrs() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("kmlkVrs");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(30, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnPrBrm() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("prBrm");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(3, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnTtr() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("ttr");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(24, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnGonUnv() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("gonUnv");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnGonHspNo() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("gonHspNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(26, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnAlcUnv() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("alcUnv");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnAlcHspNo() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("alcHspNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(26, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnOdmKynk() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("odmKynk");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnOdmAmc() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("odmAmc");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(2, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnRefBlg() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("refBlg");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnOdmAcklm() throws NoSuchFieldException {
        Field field = PaymentEntity.class.getDeclaredField("odmAcklm");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(200, column.length());
    }

    @Test
    void shouldHaveExpectedNumberOfDeclaredFields() {
        Field[] fields = PaymentEntity.class.getDeclaredFields();

        assertEquals(24, fields.length);
    }
}
