package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 31/12/17.
 */
public class ScannedProductDiscountTest {

    @Test
    public void testCurrencyConstructor() {
        Currency currency1 = new Currency("Pound Sterling", "£");

        BigDecimal savings = new BigDecimal(100);
        ScannedProductDiscount scannedProductDiscount = new ScannedProductDiscount("A", currency1, savings);

        Assert.assertNotNull(scannedProductDiscount);
        Assert.assertEquals(scannedProductDiscount.getProductSku(), "A");
        Assert.assertEquals(scannedProductDiscount.getCurrency(), currency1);
        Assert.assertEquals(scannedProductDiscount.getSavings(), savings);
    }

    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Currency currency1 = new Currency("Pound Sterling", "£");

        BigDecimal savings = new BigDecimal(100);
        ScannedProductDiscount scannedProductDiscount = new ScannedProductDiscount("A", currency1, savings);

        Field idField = ScannedProductDiscount.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(scannedProductDiscount, 1);

        Assert.assertEquals(scannedProductDiscount.getId(), 1);
    }

    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Currency currency1 = new Currency("Pound Sterling", "£");

        BigDecimal savings = new BigDecimal(100);
        ScannedProductDiscount scannedProductDiscount = new ScannedProductDiscount("A", currency1, savings);

        Field versionField = ScannedProductDiscount.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(scannedProductDiscount, 1);

        Assert.assertEquals(scannedProductDiscount.getVersion(), 1);
    }



    @Test
    public void testCheckout() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        BigDecimal savings = new BigDecimal(100);
        ScannedProductDiscount scannedProductDiscount = new ScannedProductDiscount("A", currency, savings);

        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");

        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);

        scannedProductDiscount.setCheckout(checkout);

        Assert.assertEquals(scannedProductDiscount.getCheckout(), checkout);
    }

    @Test
    public void testEquals() {

        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();

        Checkout checkout1 = new Checkout(till, tillUser, new BigDecimal(100), new BigDecimal(100), startTime, endTime);
        Checkout checkout2 = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);

        EqualsVerifier.forClass(ScannedProductDiscount.class)
                .withPrefabValues(ScannedProduct.class, new ScannedProduct(LocalDateTime.now(), "sku1", currency, new BigDecimal(0)), new ScannedProduct(LocalDateTime.now(), "sku2", currency, new BigDecimal(0)))
                .withPrefabValues(Checkout.class, checkout1, checkout2)
                .withIgnoredFields("checkout", "currency")
                .usingGetClass()
                .verify();
    }


    @Test
    public void testToString() {
        Currency currency = new Currency("Pound Sterling", "£");

        BigDecimal savings = new BigDecimal(100);
        ScannedProductDiscount scannedProductDiscount1 = new ScannedProductDiscount("A", currency, savings);
        ScannedProductDiscount scannedProductDiscount2 = new ScannedProductDiscount("A", currency, savings);

        Assert.assertTrue(scannedProductDiscount1.toString().equals(scannedProductDiscount2.toString()));
    }






}
