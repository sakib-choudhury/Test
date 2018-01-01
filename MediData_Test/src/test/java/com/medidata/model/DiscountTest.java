package com.medidata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class DiscountTest {

    @Test
    public void testConstructor() {

        Discount discount = new Discount(65, 70, 60);

        Assert.assertNotNull(discount);
        Assert.assertEquals(discount.getLowerAgeLimit(), 65);
        Assert.assertEquals(discount.getUpperAgeLimit(), 70);
        Assert.assertEquals(discount.getDiscountPercentage(), 60, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerAgeLimitLessThanZero() {
        new Discount(-1, 70, 60);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorUpperAgeLimitLessThanZero() {
        new Discount(65, -1, 60);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPercentageLessThanZero() {
        new Discount(65, 70, -1);

        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPercentageMoreThanNinetynine() {
        new Discount(65, 70, 101);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBothAgeLimitZero() {
        new Discount(0, 0, 10);

        Assert.fail();
    }

    @Test//(expected = IllegalArgumentException.class)
    public void testConstructorBothAgeLimitNotZero() {
        Discount discount = new Discount(0, 70, 60);

        Assert.assertNotNull(discount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerAgeGreaterThanUpperAgeLimit() {
        new Discount(10, 5, 10);

        Assert.fail();
    }

    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {

        EqualsVerifier.forClass(Discount.class)
                .usingGetClass()
                .verify();
    }

    @Test
    public void testHashCode() {
        Discount discount1 = new Discount(65, 70, 60);
        Discount discount2 = new Discount(65, 70, 60);

        Assert.assertTrue(discount1.equals(discount2) && discount2.equals(discount1));
        Assert.assertTrue(discount1.hashCode() == discount2.hashCode());

    }

    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Discount discount = new Discount(65, 70, 60);

        Field idField = Discount.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(discount, 1);

        Assert.assertEquals(discount.getId(), 1);
    }


    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Discount discount = new Discount(65, 70, 60);
        Field versionField = Discount.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(discount, 1);

        Assert.assertEquals(discount.getVersion(), 1);
    }
}
