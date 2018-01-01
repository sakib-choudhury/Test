package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class CurrencyTest {

    @Test
    public void testCurrencyConstructor() {
        Currency currency = new Currency("Pound Sterling", "£");
        Assert.assertEquals(currency.getName(), "Pound Sterling");
        Assert.assertEquals(currency.getIso(), "£");
    }

    @Test
    public void testCurrencyId() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        Field idField = Currency.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(currency, 1);

        Assert.assertEquals(currency.getId(), 1);
    }

    @Test
    public void testCurrencyVersion() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        Field versionField = Currency.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(currency, 1);

        Assert.assertEquals(currency.getVersion(), 1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyNullName() {
        new Currency(null, "£");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyEmptyName() {
        new Currency("", "£");
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyNullIso() {
        new Currency("Test", null);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyEmptyIso() {
        new Currency("Test", "");
        Assert.fail();
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Currency.class).usingGetClass()
                .verify();
    }

    @Test
    public void testToString() {
        Currency currency1 = new Currency("Pound Sterling", "£");
        Currency currency2 = new Currency("Pound Sterling", "£");

        Assert.assertTrue(currency1.toString().equals(currency2.toString()));
    }


}
