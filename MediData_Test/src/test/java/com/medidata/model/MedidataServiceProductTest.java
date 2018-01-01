package com.medidata.model;


import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class MedidataServiceProductTest {

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));

        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Assert.assertNotNull(medidataServiceProduct);
        Assert.assertEquals(medidataServiceProduct.getName(), "Name");
        Assert.assertEquals(medidataServiceProduct.getCurrency(), currency);
        Assert.assertEquals(medidataServiceProduct.getCost(), new BigDecimal(100));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNameNull() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        new MedidataServiceProduct(null, currency, new BigDecimal(100), medidataService);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameEmpty() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        new MedidataServiceProduct("", currency, new BigDecimal(100), medidataService);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyNull() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        new MedidataServiceProduct("Name", null, new BigDecimal(100), medidataService);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCostNull() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        new MedidataServiceProduct("Name", currency, null, medidataService);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCostLessThanZero() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        new MedidataServiceProduct("Name", currency, new BigDecimal(-1), medidataService);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testServiceNull() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        new MedidataServiceProduct("Name", currency, new BigDecimal(100), null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testServiceDetached() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        //service.setId(1);
        new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Assert.fail();
    }


    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        Assert.assertEquals(medidataServiceProduct.getId(), 1);
    }



    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductVersionField = MedidataServiceProduct.class.getDeclaredField("version");
        serviceProductVersionField.setAccessible(true);
        serviceProductVersionField.set(medidataServiceProduct, 1);

        Assert.assertEquals(medidataServiceProduct.getVersion(), 1);
    }


    @Test
    public void testName() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductNameField = MedidataServiceProduct.class.getDeclaredField("name");
        serviceProductNameField.setAccessible(true);
        serviceProductNameField.set(medidataServiceProduct, "Gamma");

        Assert.assertEquals(medidataServiceProduct.getName(), "Gamma");
    }


    @Test
    public void testCurrency() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);


        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Currency currency2 = new Currency("Pound Sterling1", "£");
        Field serviceProductCurrencyField = MedidataServiceProduct.class.getDeclaredField("currency");
        serviceProductCurrencyField.setAccessible(true);
        serviceProductCurrencyField.set(medidataServiceProduct, currency2);

        Assert.assertEquals(medidataServiceProduct.getCurrency(), currency2);
    }


    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductCostField = MedidataServiceProduct.class.getDeclaredField("cost");
        serviceProductCostField.setAccessible(true);
        serviceProductCostField.set(medidataServiceProduct, new BigDecimal(200));

        Assert.assertEquals(medidataServiceProduct.getCost(), new BigDecimal(200));
    }


    @Test
    public void testService() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);


        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductCostField = MedidataServiceProduct.class.getDeclaredField("cost");
        serviceProductCostField.setAccessible(true);
        serviceProductCostField.set(medidataServiceProduct, new BigDecimal(200));

        Assert.assertEquals(medidataServiceProduct.getMedidataService(), medidataService);
    }


    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {

        Currency currency = new Currency("Pound Sterling", "£");

        MedidataService medidataService1 = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService1, 1);


        MedidataService medidataService2 = new MedidataService("Service 2", currency, new BigDecimal(100));
        Field idField2 = MedidataService.class.getDeclaredField("id");
        idField2.setAccessible(true);
        idField2.set(medidataService2, 1);

        MedidataServiceProduct medidataServiceProduct1 = new MedidataServiceProduct("Name 1", currency, new BigDecimal(100), medidataService1);
        MedidataServiceProduct medidataServiceProduct2 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService2);

        EqualsVerifier.forClass(MedidataServiceProduct.class)
                      .usingGetClass()
                      .withPrefabValues(MedidataServiceProduct.class, medidataServiceProduct1, medidataServiceProduct2)
                      .withIgnoredFields("medidataService")
                      .verify();
    }

    @Test
    public void testHashCode() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);
        MedidataServiceProduct medidataServiceProduct1 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        MedidataServiceProduct medidataServiceProduct2 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);

        Assert.assertTrue(medidataServiceProduct1.equals(medidataServiceProduct2) && medidataServiceProduct2.equals(medidataServiceProduct1));
        Assert.assertTrue(medidataServiceProduct1.hashCode() == medidataServiceProduct2.hashCode());

    }




}
