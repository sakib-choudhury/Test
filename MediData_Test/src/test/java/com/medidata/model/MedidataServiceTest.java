package com.medidata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class MedidataServiceTest {

    @Test
    public void testConstructor() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Assert.assertNotNull(medidataService);
        Assert.assertEquals(medidataService.getName(), "Service 1");
        Assert.assertEquals(medidataService.getCurrency(),  currency);
        Assert.assertEquals(medidataService.getCost(),  new BigDecimal(100));

    }



    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        Assert.assertEquals(medidataService.getId(), 1);
    }


    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));

        Field versionField = MedidataService.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(medidataService, 1);

        Assert.assertEquals(medidataService.getVersion(), 1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNameNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        new MedidataService(null, currency, new BigDecimal(100));

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNameEmpty() {
        Currency currency = new Currency("Pound Sterling", "£");
        new MedidataService("", currency, new BigDecimal(100));

        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCurrencyNull() {
        new MedidataService("Service 1", null, new BigDecimal(100));

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCostNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        new MedidataService("Service 1", currency, null);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCostLessThanZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        new MedidataService("Service 1", currency, new BigDecimal(-1));

        Assert.fail();
    }


    @Test
    public void testServiceProductList() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataServiceProduct, 1);

        medidataService.addServiceProduct(medidataServiceProduct);

        Assert.assertEquals(medidataService.getMedidataServiceProductList().size(), 1);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddServiceProductNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        medidataService.addServiceProduct(null);

        Assert.fail();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddServiceProductDetached() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        medidataService.addServiceProduct(medidataServiceProduct);

        Assert.fail();
    }


    @Test
    public void testAddServiceProduct2ndTime() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataServiceProduct, 1);

        MedidataServiceProduct medidataServiceProduct2 = new MedidataServiceProduct("Name 2", currency, new BigDecimal(100), medidataService);
        serviceIdField.set(medidataServiceProduct2, 2);

        medidataService.addServiceProduct(medidataServiceProduct);
        medidataService.addServiceProduct(medidataServiceProduct2);

        Assert.assertEquals(medidataService.getMedidataServiceProductList().size(), 2);
    }


    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField1 = MedidataService.class.getDeclaredField("id");
        serviceField1.setAccessible(true);
        serviceField1.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct1 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField1 = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField1.setAccessible(true);
        serviceProductIdField1.set(medidataServiceProduct1, 1);


        MedidataServiceProduct medidataServiceProduct2 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField2 = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField2.setAccessible(true);
        serviceProductIdField2.set(medidataServiceProduct1, 1);

        EqualsVerifier.forClass(MedidataService.class)
                        .withPrefabValues(MedidataServiceProduct.class, medidataServiceProduct1, medidataServiceProduct2)
                        .withIgnoredFields("medidataServiceProductList")
                        .usingGetClass()
                        .verify();
    }



    @Test
    public void testHashCode() {
        Currency currency1 = new Currency("Pound Sterling", "£");
        Currency currency2 = new Currency("Pound Sterling", "£");

        Assert.assertTrue(currency1.equals(currency2) && currency2.equals(currency1));
        Assert.assertTrue(currency1.hashCode() == currency2.hashCode());

    }






}
