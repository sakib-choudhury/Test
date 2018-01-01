package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 25/12/17.
 */
public class ScannedProductTest {

    @Test
    public void testId() throws IllegalAccessException, NoSuchFieldException {
        Currency currency = new Currency("Pound Sterling", "£");
        ScannedProduct scannedProduct = new ScannedProduct(LocalDateTime.now(), "A", currency, new BigDecimal(10));

        Field idField = ScannedProduct.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(scannedProduct, 1);

        Assert.assertEquals(scannedProduct.getId(), 1);
    }

    @Test
    public void testVersion() throws IllegalAccessException, NoSuchFieldException {
        Currency currency = new Currency("Pound Sterling", "£");
        ScannedProduct scannedProduct = new ScannedProduct(LocalDateTime.now(), "A", currency, new BigDecimal(10));

        Field versionField = ScannedProduct.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(scannedProduct, 1);

        Assert.assertEquals(scannedProduct.getVersion(), 1);
    }

    @Test
    public void testConstructor() throws IllegalAccessException, NoSuchFieldException {
        Currency currency = new Currency("Pound Sterling", "£");
        LocalDateTime scanTime = LocalDateTime.now();
        ScannedProduct scannedProduct = new ScannedProduct(scanTime, "A", currency, new BigDecimal(10));

        Assert.assertEquals(scannedProduct.getScanDateTime(), scanTime);
        Assert.assertEquals(scannedProduct.getProductSku(), "A");
        Assert.assertEquals(scannedProduct.getCurrency(), currency);
        Assert.assertEquals(scannedProduct.getPrice(), new BigDecimal(10));
    }

    @Test
    public void testCheckout() throws IllegalAccessException, NoSuchFieldException {
        Currency currency = new Currency("Pound Sterling", "£");
        ScannedProduct scannedProduct = new ScannedProduct(LocalDateTime.now(), "A", currency, new BigDecimal(10));
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Till till = new Till(10, 10, currency);

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(10), LocalDateTime.now(), LocalDateTime.now());

        Field checkoutField = ScannedProduct.class.getDeclaredField("checkout");
        checkoutField.setAccessible(true);
        checkoutField.set(scannedProduct, checkout);

        Assert.assertEquals(scannedProduct.getCheckout(), checkout);
    }

    @Test
    public void testEquals() {
        Currency currency = new Currency("Pound Sterling", "£");

        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Till till = new Till(10, 10, currency);

        LocalDateTime testTime = LocalDateTime.now();
        Checkout checkout1 = new Checkout(till, tillUser, new BigDecimal(100), new BigDecimal(10), testTime, testTime);
        Checkout checkout2 = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(10), testTime, testTime);


        EqualsVerifier.forClass(ScannedProduct.class)
                      .usingGetClass()
                      .withPrefabValues(Checkout.class, checkout1, checkout2)
                      .withIgnoredFields("checkout", "currency")
                      .verify();
    }

    @Test
    public void testToString() {
        Currency currency = new Currency("Pound Sterling", "£");
        LocalDateTime scanTime = LocalDateTime.now();
        ScannedProduct scannedProduct1 = new ScannedProduct(scanTime, "A", currency, new BigDecimal(10));
        ScannedProduct scannedProduct2 = new ScannedProduct(scanTime, "A", currency, new BigDecimal(10));

        Assert.assertTrue(scannedProduct1.toString().equals(scannedProduct2.toString()));

    }





}
