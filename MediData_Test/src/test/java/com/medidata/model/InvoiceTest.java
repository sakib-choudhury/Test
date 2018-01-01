package com.medidata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class InvoiceTest {

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Assert.assertNotNull(invoice);
        Assert.assertEquals(invoice.getCustomer(), customer);
        Assert.assertEquals(invoice.getCurrency(), currency);
        Assert.assertEquals(invoice.getDateTime(), nowTime);

    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCustomerNull() throws NoSuchFieldException, IllegalAccessException {

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        new Invoice(null, currency, nowTime);

        Assert.fail();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCustomerInvalidId() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        new Invoice(customer, currency, nowTime);

        Assert.fail();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCurrencyNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        new Invoice(customer, null, nowTime);

        Assert.fail();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCurrencyInvalidId() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");

        LocalDateTime nowTime =  LocalDateTime.now();

        new Invoice(customer, currency, nowTime);

        Assert.fail();

    }




    @Test(expected = IllegalArgumentException.class)
    public void testConstructorDateTimeNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        new Invoice(customer, currency, null);

        Assert.fail();

    }


    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);


        Field idField = Invoice.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(invoice, 1);

        Assert.assertEquals(invoice.getId(), 1);
    }


    @Test
    public void testVersion() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);

        Field versionField = Invoice.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(invoice, 1);

        Assert.assertEquals(invoice.getVersion(), 1);
    }



    /*@Test(expected = IllegalArgumentException.class)
    public void testAddInvoiceLineNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);

        invoice.addInvoiceLine(null);

        Assert.fail();
    }*/


    /*@Test(expected = IllegalArgumentException.class)
    public void testAddInvoiceLineIdInvalid() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField2 = MedidataService.class.getDeclaredField("id");
        idField2.setAccessible(true);
        idField2.set(medidataService, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);

        invoice.addInvoiceLine(invoiceLine);

        Assert.fail();
    }*/


    /*@Test
    public void testAddInvoiceLine() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);


        invoice.addInvoiceLine(invoiceLine);

        Assert.assertEquals(invoice.getInvoiceLineList().size(), 1);
    }*/


    /*@Test
    public void testAddInvoiceLineInvoiceListNotNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        MedidataService medidataService2 = new MedidataService("Service 2", currency, new BigDecimal(100));
        serviceField.set(medidataService2, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        invoice.addInvoiceLine(invoiceLine);

        InvoiceLine invoiceLine2 = new InvoiceLine(invoice, medidataService2);
        invoiceLineIdField.set(invoiceLine2, 2);

        invoice.addInvoiceLine(invoiceLine2);

        Assert.assertEquals(invoice.getInvoiceLineList().size(), 2);
    }*/


    /*@Test
    public void testAddInvoiceLineInvoiceListContainsGivenInvoiceLine() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        MedidataService medidataService2 = new MedidataService("Service 2", currency, new BigDecimal(100));
        serviceField.set(medidataService2, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        invoice.addInvoiceLine(invoiceLine);

        invoice.addInvoiceLine(invoiceLine);

        Assert.assertEquals(invoice.getInvoiceLineList().size(), 1);
    }*/




    @Test
    public void testCalculateTotalInvoiceListNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(0));
    }


    /*@Test
    public void testCalculateTotalInvoiceListEmpty() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        Field listField = Invoice.class.getDeclaredField("invoiceLineList");
        listField.setAccessible(true);
        listField.set(invoice, new ArrayList<InvoiceLine>());


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(0));
    }*/



    /*@Test
    public void testCalculateTotalDiscountNotNull() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Discount discount = new Discount(65, 70, 60);
        Field idField = Discount.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(discount, 1);
        customer.addDiscount(discount);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        //invoice.addInvoiceLine(invoiceLine);


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(40));
    }*/


    /*@Test
    public void testCalculateTotalInvoiceListForService() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        //invoice.addInvoiceLine(invoiceLine);


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(100));
    }*/




    /*@Test
    public void testCalculateTotalInvoiceListForServiceWithInsuranceNotForService() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);
        customer.addInsurance(insurance);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService2 = new MedidataService("Service 2", currency, new BigDecimal(100));
        serviceField.set(medidataService2, 2);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService2);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        //invoice.addInvoiceLine(invoiceLine);


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(100));
    }*/



    /*@Test
    public void testCalculateTotalInvoiceListForServiceWithInsuranceForService() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);
        customer.addInsurance(insurance);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        //invoice.addInvoiceLine(invoiceLine);


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(85));
    }*/



    /*@Test
    public void testCalculateTotalInvoiceListForServiceWithInsuranceForServiceProduct() throws NoSuchFieldException, IllegalAccessException {
        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceField = MedidataService.class.getDeclaredField("id");
        serviceField.setAccessible(true);
        serviceField.set(medidataService, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);
        customer.addInsurance(insurance);

        LocalDateTime nowTime =  LocalDateTime.now();

        Invoice invoice = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataServiceProduct);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine, 1);

        //invoice.addInvoiceLine(invoiceLine);


        Assert.assertEquals(invoice.getTotal(), new BigDecimal(100));
    }*/


    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService1 = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService1, 1);


        MedidataService medidataService2 = new MedidataService("Service 2", currency, new BigDecimal(100));
        Field idField2 = MedidataService.class.getDeclaredField("id");
        idField2.setAccessible(true);
        idField2.set(medidataService2, 1);

        MedidataServiceProduct medidataServiceProduct1 = new MedidataServiceProduct("Name 1", currency, new BigDecimal(100), medidataService1);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct1, 1);


        MedidataServiceProduct medidataServiceProduct2 = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService2);
        serviceProductIdField.set(medidataServiceProduct2, 1);

        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataServiceProduct1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);

        InvoiceLine invoiceLine2 = new InvoiceLine(invoice, medidataServiceProduct2);
        invoiceLineIdField.set(invoiceLine2, 2);

        EqualsVerifier.forClass(Invoice.class)
                .usingGetClass()
                .withPrefabValues(MedidataServiceProduct.class, medidataServiceProduct1, medidataServiceProduct2)
                .withPrefabValues(InvoiceLine.class, invoiceLine1, invoiceLine2)
                .withIgnoredFields("customer", "total")
                .verify();
    }

    @Test
    public void testHashCode() throws NoSuchFieldException, IllegalAccessException {
        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService1 = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService1, 1);


        MedidataService medidataService2 = new MedidataService("Service 2", currency, new BigDecimal(100));
        Field idField2 = MedidataService.class.getDeclaredField("id");
        idField2.setAccessible(true);
        idField2.set(medidataService2, 1);

        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        LocalDateTime nowTime = LocalDateTime.now();

        Invoice invoice1 = new Invoice(customer, currency, nowTime);
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice1, 1);

        Invoice invoice2 = new Invoice(customer, currency, nowTime);
        invoiceIdField.set(invoice2, 1);

        Assert.assertTrue(invoice1.equals(invoice2) && invoice2.equals(invoice1));
        Assert.assertTrue(invoice1.hashCode() == invoice2.hashCode());

    }


}
