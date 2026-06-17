package com.openbanking.yos.domain.account.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AccountEntityAnnotationTest {

    @Test
    void shouldHaveEntityAnnotation() {
        assertTrue(AccountEntity.class.isAnnotationPresent(Entity.class));
    }

    @Test
    void shouldHaveTableAnnotationWithCorrectName() {
        Table table = AccountEntity.class.getAnnotation(Table.class);

        assertNotNull(table);
        assertEquals("ohvps_hesap", table.name());
    }

    @Test
    void shouldHaveIdAnnotationOnIdField() throws NoSuchFieldException {
        Field idField = AccountEntity.class.getDeclaredField("id");

        assertTrue(idField.isAnnotationPresent(Id.class));
    }

    @Test
    void shouldHaveGeneratedValueWithIdentityStrategy() throws NoSuchFieldException {
        Field idField = AccountEntity.class.getDeclaredField("id");
        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);

        assertNotNull(generatedValue);
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
    }

    @Test
    void shouldHaveUniqueAndNotNullConstraintOnHspRef() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspRef");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertTrue(column.unique());
        assertFalse(column.nullable());
        assertEquals(40, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintOnRizaNo() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("rizaNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHspNo() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspNo");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(26, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnHspShb() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspShb");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnSubeAdi() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("subeAdi");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(50, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnKisaAd() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("kisaAd");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(50, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnPrBrm() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("prBrm");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(3, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnHspTur() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspTur");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(1, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnHspTip() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspTip");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(32, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHspUrunAdi() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspUrunAdi");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(140, column.length());
    }

    @Test
    void shouldHaveNotNullConstraintAndCorrectLengthOnHspDrm() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hspDrm");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertFalse(column.nullable());
        assertEquals(50, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnHhsKod() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("hhsKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCorrectColumnLengthOnYosKod() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("yosKod");
        Column column = field.getAnnotation(Column.class);

        assertNotNull(column);
        assertEquals(4, column.length());
    }

    @Test
    void shouldHaveCreationTimestampOnOlusZmn() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("olusZmn");

        assertTrue(field.isAnnotationPresent(CreationTimestamp.class));
    }

    @Test
    void shouldHaveUpdateTimestampOnGnclZmn() throws NoSuchFieldException {
        Field field = AccountEntity.class.getDeclaredField("gnclZmn");

        assertTrue(field.isAnnotationPresent(UpdateTimestamp.class));
    }

    @Test
    void shouldHaveExpectedNumberOfDeclaredFields() {
        Field[] fields = AccountEntity.class.getDeclaredFields();

        assertEquals(17, fields.length);
    }
}
