package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 21/12/17.
 */
public class CheckoutTest {

    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), LocalDateTime.now(), LocalDateTime.now());
        Field idField = Checkout.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(checkout, 1);

        Assert.assertEquals(checkout.getId(), 1);
    }

    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), LocalDateTime.now(), LocalDateTime.now());
        Field versionField = Checkout.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(checkout, 1);

        Assert.assertEquals(checkout.getVersion(), 1);
    }


    @Test
    public void testTill() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), LocalDateTime.now(), LocalDateTime.now());


        Assert.assertEquals(checkout.getTill(), till);
    }

    @Test
    public void testTillUser() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), LocalDateTime.now(), LocalDateTime.now());


        Assert.assertEquals(checkout.getTillUser(), tillUser);
    }


    @Test
    public void testTillStartTime() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);


        Assert.assertEquals(checkout.getStartTime(), startTime);
    }


    @Test
    public void testTillEndTime() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);


        Assert.assertEquals(checkout.getEndTime(), endTime);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testTillConstructorTillNull() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        //Currency currency = new Currency("Pound Sterling", "£");
        //Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        new Checkout(null, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTillUserNull() throws NoSuchFieldException, IllegalAccessException {
        //TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        new Checkout(till, null, new BigDecimal(10), new BigDecimal(100), startTime, endTime);

    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSubTotalNull() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        new Checkout(till, tillUser, null, new BigDecimal(100), startTime, endTime);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTotalNull() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        new Checkout(till, tillUser, new BigDecimal(100), null, startTime, endTime);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorStartTimeNull() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        //LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        new Checkout(till, tillUser, new BigDecimal(100), new BigDecimal(100), null, endTime);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEndTimeNull() throws NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        //LocalDateTime endTime = LocalDateTime.now();
        new Checkout(till, tillUser, new BigDecimal(100), new BigDecimal(100), startTime, null);
    }


    @Test
    public void testEquals() {
        Currency currency = new Currency("Pound Sterling", "£");

        EqualsVerifier.forClass(Checkout.class)
                      .withPrefabValues(ScannedProduct.class, new ScannedProduct(LocalDateTime.now(), "sku1", currency, new BigDecimal(0)), new ScannedProduct(LocalDateTime.now(), "sku2", currency, new BigDecimal(0)))
                      .withPrefabValues(ScannedProductDiscount.class, new ScannedProductDiscount("sku1", currency, new BigDecimal(0)), new ScannedProductDiscount("sku2", currency, new BigDecimal(0)))
                      .withIgnoredFields("scannedProductSet", "scannedProductDiscountSet", "till", "tillUser")
                      .usingGetClass()
                      .verify();
    }

    @Test
    public void testToString() {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();

        Checkout checkout1 = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);
        Checkout checkout2 = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);

        Assert.assertTrue(checkout1.toString().equals(checkout2.toString()));

    }





}
