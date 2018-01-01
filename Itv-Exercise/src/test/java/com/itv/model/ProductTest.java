package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 20/12/17.
 */
public class ProductTest {

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Assert.assertEquals(product.getSku(), "A");
        Assert.assertEquals(product.getCurrency(), currency);
        Assert.assertEquals(product.getPrice(), new BigDecimal(50));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSkuNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Product(null, currency, new BigDecimal(50));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSkuEmpty() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Product("", currency, new BigDecimal(50));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCurrencyNull() {
        new Product("A", null, new BigDecimal(50));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCurrencyDetached() {
        Currency currency = new Currency("Pound Sterling", "£");
        new Product("A", currency, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPriceNull() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        new Product("A", currency, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPriceZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        new Product("A", currency, new BigDecimal(0));
    }

    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        Assert.assertEquals(product.getId(), 1);
    }

    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field versionField = Product.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(product, 1);

        Assert.assertEquals(product.getVersion(), 1);
    }


    @Test
    public void testSpecialPrice() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(3, 40);

        Assert.assertEquals(product.getSpecialPrice().getSavings(), new BigDecimal(110));
    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddSpecialPriceUnitLessThanZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(-1, 40);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddSpecialPriceUnitZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(0, 40);
    }





    @Test(expected = IllegalArgumentException.class)
    public void testAddSpecialPriceTotalPriceLessThanZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(2, -1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddSpecialPriceTotalPriceZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(2, 0);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddSpecialPriceSavingsZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(2, 100);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddSpecialPriceSavingsLessThanZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        Product product = new Product("A", currency, new BigDecimal(50));

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1);

        product.addSpecialPrice(2, 110);
    }



    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Product.class).usingGetClass().withIgnoredFields("specialPrice").verify();
    }

    @Test
    public void testToString() {
        Currency currency = new Currency("Pound Sterling", "£");

        Product product1 = new Product("A", currency, new BigDecimal(50));
        Product product2 = new Product("A", currency, new BigDecimal(50));

        Assert.assertTrue(product1.toString().equals(product2.toString()));

    }








}
