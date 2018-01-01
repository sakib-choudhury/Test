package com.medidata.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public class InvoiceLineTest {

    @Test
    public void testConstructorWithService() throws NoSuchFieldException, IllegalAccessException {

        Discount discount = new Discount(65, 70, 60);
        Field idField = Discount.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(discount, 1);

        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

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
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataService);

        Assert.assertNotNull(invoiceLine);
        Assert.assertEquals(invoiceLine.getInvoice(), invoice);
        Assert.assertEquals(invoiceLine.getMedidataService(), medidataService);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceInvoiceNull() throws NoSuchFieldException, IllegalAccessException {

        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        new InvoiceLine(null, medidataService);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceInvoiceInvalidId() throws NoSuchFieldException, IllegalAccessException {

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

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        new InvoiceLine(invoice, medidataService);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceNull() throws NoSuchFieldException, IllegalAccessException {

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

        new InvoiceLine(invoice, (MedidataService) null);

        Assert.fail();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceInvalidId() throws NoSuchFieldException, IllegalAccessException {

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

        new InvoiceLine(invoice, medidataService);

        Assert.fail();
    }




    @Test
    public void testConstructorWithServiceProduct() throws NoSuchFieldException, IllegalAccessException {

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
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataServiceProduct);

        Assert.assertNotNull(invoiceLine);
        Assert.assertEquals(invoiceLine.getInvoice(), invoice);
        Assert.assertEquals(invoiceLine.getMedidataServiceProduct(), medidataServiceProduct);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceProductInvoiceNull() throws NoSuchFieldException, IllegalAccessException {

        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Currency currency = new Currency("Pound Sterling", "£");
        Field currencyIdField = Currency.class.getDeclaredField("id");
        currencyIdField.setAccessible(true);
        currencyIdField.set(currency, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        new InvoiceLine(null, medidataServiceProduct);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceProductInvoiceInvalidId() throws NoSuchFieldException, IllegalAccessException {

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

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        new InvoiceLine(invoice, medidataServiceProduct);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceProductNull() throws NoSuchFieldException, IllegalAccessException {

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
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        new InvoiceLine(invoice, (MedidataServiceProduct)null);

        Assert.fail();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithServiceProductInvalidId() throws NoSuchFieldException, IllegalAccessException {

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
        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);

        new InvoiceLine(invoice, medidataServiceProduct);

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
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));

        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataServiceProduct);

        Field idField = InvoiceLine.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(invoiceLine, 1);

        Assert.assertEquals(invoiceLine.getId(), 1);
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
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataService medidataService = new MedidataService("Service 1", currency, new BigDecimal(100));

        Field serviceIdField = MedidataService.class.getDeclaredField("id");
        serviceIdField.setAccessible(true);
        serviceIdField.set(medidataService, 1);

        MedidataServiceProduct medidataServiceProduct = new MedidataServiceProduct("Name", currency, new BigDecimal(100), medidataService);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct, 1);

        InvoiceLine invoiceLine = new InvoiceLine(invoice, medidataServiceProduct);

        Field versionField = InvoiceLine.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(invoiceLine, 1);

        Assert.assertEquals(invoiceLine.getVersion(), 1);
    }


    @Test
    public void testCalculateTotalForMedidataServiceInsuranceForAnotherService() throws NoSuchFieldException, IllegalAccessException {

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
        idField2.set(medidataService2, 2);

        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService2, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataService1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);


        Assert.assertEquals(invoiceLine1.getTotal(), new BigDecimal(100));


    }




    @Test
    public void testCalculateTotalForMedidataServiceDifferentService() throws NoSuchFieldException, IllegalAccessException {

        Currency currency1 = new Currency("Pound Sterling", "£");
        Field currencyIdField1 = Currency.class.getDeclaredField("id");
        currencyIdField1.setAccessible(true);
        currencyIdField1.set(currency1, 1);

        Currency currency2 = new Currency("Pound Sterling", "£");
        Field currencyIdField2 = Currency.class.getDeclaredField("id");
        currencyIdField2.setAccessible(true);
        currencyIdField2.set(currency2, 2);

        MedidataService medidataService1 = new MedidataService("Service 1", currency1, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService1, 1);

        MedidataService medidataService2 = new MedidataService("Service 1", currency2, new BigDecimal(100));
        idField.set(medidataService2, 2);


        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService1, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Invoice invoice = new Invoice(customer, currency2, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataService2);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);


        Assert.assertEquals(invoiceLine1.getTotal(), new BigDecimal(100));


    }



    @Test
    public void testCalculateTotalForMedidataServiceInsuranceWithSameCurrency() throws NoSuchFieldException, IllegalAccessException {

        Currency currency1 = new Currency("Pound Sterling", "£");
        Field currencyIdField1 = Currency.class.getDeclaredField("id");
        currencyIdField1.setAccessible(true);
        currencyIdField1.set(currency1, 1);


        MedidataService medidataService1 = new MedidataService("Service 1", currency1, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService1, 1);

        MedidataService medidataService2 = new MedidataService("Service 1", currency1, new BigDecimal(100));
        idField.set(medidataService2, 2);


        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService1, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Invoice invoice = new Invoice(customer, currency1, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataService1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);


        Assert.assertEquals(invoiceLine1.getTotal(), new BigDecimal(85));


    }



    @Test
    public void testCalculateTotalForMedidataServiceSameServiceSameCurrency() throws NoSuchFieldException, IllegalAccessException {

        Currency currency1 = new Currency("Pound Sterling", "£");
        Field currencyIdField1 = Currency.class.getDeclaredField("id");
        currencyIdField1.setAccessible(true);
        currencyIdField1.set(currency1, 1);

        Currency currency2 = new Currency("Pound Sterling", "£");
        Field currencyIdField2 = Currency.class.getDeclaredField("id");
        currencyIdField2.setAccessible(true);
        currencyIdField2.set(currency2, 2);

        MedidataService medidataService1 = new MedidataService("Service 1", currency1, new BigDecimal(100));
        Field idField = MedidataService.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(medidataService1, 1);


        Customer customer = new Customer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Field customerIdField = Customer.class.getDeclaredField("id");
        customerIdField.setAccessible(true);
        customerIdField.set(customer, 1);

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService1, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Invoice invoice = new Invoice(customer, currency2, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataService1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);


        Assert.assertEquals(invoiceLine1.getTotal(), new BigDecimal(85));


    }




    @Test
    public void testCalculateTotalWithPattern() throws NoSuchFieldException, IllegalAccessException {

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

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService2, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataService1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);


        Assert.assertEquals(invoiceLine1.calculateTotal("#.00"), "100.00");


    }



    @Test
    public void testCalculateTotalWithTwoDecimalPattern() throws NoSuchFieldException, IllegalAccessException {

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

        Insurance insurance = new Insurance("Name", "Details", new BigDecimal(100), medidataService2, 15);
        Field insuranceIdField = Insurance.class.getDeclaredField("id");
        insuranceIdField.setAccessible(true);
        insuranceIdField.set(insurance, 1);

        customer.addInsurance(insurance);

        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataService1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);


        Assert.assertEquals(invoiceLine1.calculateTotalToTwoDecimal(), "100.00");


    }



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

        EqualsVerifier.forClass(InvoiceLine.class)
                .usingGetClass()
                .withPrefabValues(MedidataServiceProduct.class, medidataServiceProduct1, medidataServiceProduct2)
                .withPrefabValues(InvoiceLine.class, invoiceLine1, invoiceLine2)
                .withIgnoredFields("invoice", "medidataServiceProduct", "medidataService", "total")
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

        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        Field invoiceIdField = Invoice.class.getDeclaredField("id");
        invoiceIdField.setAccessible(true);
        invoiceIdField.set(invoice, 1);

        MedidataServiceProduct medidataServiceProduct1 = new MedidataServiceProduct("Name 1", currency, new BigDecimal(100), medidataService1);
        Field serviceProductIdField = MedidataServiceProduct.class.getDeclaredField("id");
        serviceProductIdField.setAccessible(true);
        serviceProductIdField.set(medidataServiceProduct1, 1);


        InvoiceLine invoiceLine1 = new InvoiceLine(invoice, medidataServiceProduct1);
        Field invoiceLineIdField = InvoiceLine.class.getDeclaredField("id");
        invoiceLineIdField.setAccessible(true);
        invoiceLineIdField.set(invoiceLine1, 1);

        InvoiceLine invoiceLine2 = new InvoiceLine(invoice, medidataServiceProduct1);
        invoiceLineIdField.set(invoiceLine2, 1);


        Assert.assertTrue(invoiceLine1.equals(invoiceLine2) && invoiceLine2.equals(invoiceLine1));
        Assert.assertTrue(invoiceLine1.hashCode() == invoiceLine2.hashCode());

    }





}
