package com.medidata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class InsuranceTest {

    @Test
    public void testConstructor() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Assert.assertNotNull(insurance);
        Assert.assertEquals(insurance.getName(), "Name");
        Assert.assertEquals(insurance.getDetails(), "Details");
        Assert.assertEquals(insurance.getCurrency(), currency);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNameNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance(null, "Details", new BigDecimal(100), medidataService, 15);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNameEmpty() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("", "Details", new BigDecimal(100), medidataService, 15);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDetailsNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", null, new BigDecimal(100), medidataService, 15);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDetailsEmpty() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", "", new BigDecimal(100), medidataService, 15);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCostNull() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", "Details", null, medidataService, 15);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorServiceNull() {
        new Insurance("Name", "Details", new BigDecimal(100), null, 15);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDiscountZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", "Details", new BigDecimal(100), medidataService, 0);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDiscountLessThanZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", "Details", new BigDecimal(100), medidataService, -1);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDiscountMoreThanNinetyNine() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", "Details", new BigDecimal(100), medidataService, 100);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCostLessThanZero() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        new Insurance("Name", "Details", new BigDecimal(-1), medidataService, 99);
        Assert.fail();
    }


    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field idField = Insurance.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(insurance, 1);

        Assert.assertEquals(insurance.getId(), 1);
    }


    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field versionField = Insurance.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(insurance, 1);

        Assert.assertEquals(insurance.getVersion(), 1);
    }



    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field costField = Insurance.class.getDeclaredField("cost");
        costField.setAccessible(true);
        costField.set(insurance, new BigDecimal(100));

        Assert.assertEquals(insurance.getCost(), new BigDecimal(100));
    }



    @Test
    public void testService() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService1 = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService1, 15);

        MedidataService medidataService2 = new MedidataService("Service 1", currency, new BigDecimal(100));

        Field serviceField = Insurance.class.getDeclaredField("medidataService");
        serviceField.setAccessible(true);
        serviceField.set(insurance, medidataService2);


        Assert.assertEquals(insurance.getMedidataService(), medidataService2);
    }


    @Test
    public void testDiscountPercentage() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService1 = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService1, 15);

        Field discountPercentageField = Insurance.class.getDeclaredField("discountPercentage");
        discountPercentageField.setAccessible(true);
        discountPercentageField.set(insurance, 99);


        Assert.assertEquals(insurance.getDiscountPercentage(), 99, 0);
    }


    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct1 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField1 = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField1.setAccessible(true);
        serviceProductIdField1.set(medidataServiceProduct1, 1);


        MedidataServiceProduct medidataServiceProduct2 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField2 = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField2.setAccessible(true);
        serviceProductIdField2.set(medidataServiceProduct1, 1);

        EqualsVerifier.forClass(Insurance.class)
                .usingGetClass()
                .withIgnoredFields("medidataService", "currency")
                .withPrefabValues(MedidataServiceProduct.class, medidataServiceProduct1, medidataServiceProduct2)
                .verify();
    }

    @Test
    public void testHashCode() {
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance1 = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Insurance insurance2 = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);

        Assert.assertTrue(insurance1.equals(insurance2) && insurance2.equals(insurance1));
        Assert.assertTrue(insurance1.hashCode() == insurance2.hashCode());

    }




}
