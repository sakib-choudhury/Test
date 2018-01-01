package com.medidata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class CustomerTest {

    @Test
    public void testConstructor() {
        LocalDate dateOfBirth = LocalDate.now();
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", dateOfBirth, "harry.osbourne@gmail.com");
        Assert.assertNotNull(customer);
        Assert.assertEquals(customer.getTitle(), Title.MR);
        Assert.assertEquals(customer.getFirstName(), "Harry");
        Assert.assertEquals(customer.getLastName(), "Osbourne");
        Assert.assertEquals(customer.getEmail(), "harry.osbourne@gmail.com");
        Assert.assertEquals(customer.getDateOfBirth(), dateOfBirth);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTitleNull() {
        new Customer(null, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFirstNameNull() {
        new Customer(Title.MR, null, "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFirstNameEmpty() {
        new Customer(Title.MR, "", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLastNameNull() {
        new Customer(Title.MR, "Harry", null, LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLastNameEmpty() {
        new Customer(Title.MR, "Harry", "", LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDOBNull() {
        new Customer(Title.MR, "Harry", "Osbourne", null, "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmailNull() {
        new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmailEmpty() {
        new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "");
        Assert.fail();
    }




    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field idField = Customer.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(customer, 1);

        Assert.assertEquals(customer.getId(), 1);
    }


    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field versionField = Customer.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(customer, 1);

        Assert.assertEquals(customer.getVersion(), 1);
    }



    @Test
    public void testInsurance() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);
        customer.addInsurance(insurance);

        Assert.assertEquals(customer.getInsurance(), insurance);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddDiscountNull() {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        customer.addDiscount(null);
        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddDiscountDetached() {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Discount discount = new Discount(65, 70, 60);
        customer.addDiscount(discount);

        Assert.fail();
    }


    @Test
    public void testAddDiscount() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Discount discount = new Discount(65, 70, 60);
        Field idField = Discount.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(discount, 1);
        customer.addDiscount(discount);

        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Assert.assertEquals(customer.getInsurance(), insurance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInsuranceNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        /*Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);*/

        customer.addInsurance(null);

        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddInsuranceIdZero() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        /*Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);*/

        customer.addInsurance(insurance);

        Assert.fail();
    }



    @Test
    public void testAddInsurance() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Currency currency = new Currency("Pound Sterling", "£");
        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Assert.assertEquals(customer.getInsurance(), insurance);
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

        EqualsVerifier.forClass(Customer.class)
                .usingGetClass()
                .withIgnoredFields("insurance", "discount")
                .withPrefabValues(MedidataServiceProduct.class, medidataServiceProduct1, medidataServiceProduct2)
                .verify();
    }

    @Test
    public void testHashCode() {
        Customer customer1 = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Customer customer2 = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Assert.assertTrue(customer1.equals(customer2) && customer2.equals(customer1));
        Assert.assertTrue(customer1.hashCode() == customer2.hashCode());

    }





}
