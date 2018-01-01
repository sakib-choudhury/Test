package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 31/12/17.
 */
public class TillTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorStoreZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Till(0, 19, currency);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorStoreLessThanZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Till(-1, 19, currency);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTillNoZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Till(10, 0, currency);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTillNoLessThanZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Till(10, -1, currency);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCurrencyNull() {
        new Till(10, 10, null);
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Till.class)
                .usingGetClass()
                .withIgnoredFields("currency")
                .verify();
    }

    @Test
    public void testToString() {
        Currency currency = new Currency("Pound Sterling", "£");
        Till till1 = new Till(10, 10, currency);
        Till till2 = new Till(10, 10, currency);

        Assert.assertTrue(till1.toString().equals(till2.toString()));
    }
}
