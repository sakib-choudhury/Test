package com.medidata.service;

import com.medidata.model.*;
import com.medidata.service.impl.BillingServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sakibchoudhury on 04/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class BillingServiceTest {

    @Autowired
    private BillingService billingService;

    @Test
    public void testCreateCurrency() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Assert.assertTrue(currency.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testCreateCurrencyNameNull() throws BillingServiceException {
        Currency currency = billingService.createCurrency(null, "£");
        Assert.assertTrue(currency.getId() > 0);
    }

    @Test
    public void testCustomer() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.assertTrue(customer.getId() > 0);
    }


    @Test
    public void testCustomerWithDiscount() throws BillingServiceException {
        Discount discount = billingService.createDiscount(65, 70, 60);
        LocalDate customerDOB = LocalDate.now().minusYears(67);
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", customerDOB, "harry.osbourne@gmail.com");
        Assert.assertEquals(customer.getDiscount(), discount);
    }

    @Test(expected = BillingServiceException.class)
    public void testCustomerFail() throws BillingServiceException {
        billingService.createCustomer(null, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test(expected = BillingServiceException.class)
    public void testCustomerFailForAgeMoreThanMax() throws BillingServiceException {
        LocalDate localDate = LocalDate.now().minusYears(151);
        billingService.createCustomer(Title.MR, "Harry", "Osbourne", localDate, "harry.osbourne@gmail.com");
        Assert.fail();
    }

    @Test
    public void testDiscount() throws BillingServiceException {
        Discount discount = billingService.createDiscount(65, 70, 60);
        Assert.assertTrue(discount.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testDiscountFail() throws BillingServiceException {
        billingService.createDiscount(-1, 70, 60);
        Assert.fail();
    }

    @Test
    public void testService() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Assert.assertTrue(medidataService.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testServiceFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        billingService.createService(null, currency, 100.10);
        Assert.fail();
    }

    @Test
    public void testInsurance() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Name", "Details", 100.10, medidataService, 15);

        Assert.assertTrue(insurance.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testInsuranceFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        billingService.createInsurance(null, "Details", 100.10, medidataService, 15);

        Assert.fail();
    }

    @Test
    public void testInvoice() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = billingService.createInvoice(customer, currency);
        Assert.assertTrue(invoice.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testInvoiceFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        billingService.createInvoice(null, currency);
        Assert.fail();
    }

    @Test
    public void testServiceProduct() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = billingService.createServiceProduct("Name", currency, 100.10, medidataService);

        Assert.assertTrue(medidataServiceProduct.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testServiceProductFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        billingService.createServiceProduct(null, currency, 100.10, medidataService);

        Assert.fail();
    }


    @Test
    public void testInvoiceLineWithService() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = billingService.createInvoice(customer, currency);
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        InvoiceLine invoiceLine = billingService.createInvoiceLine(invoice, medidataService);

        Assert.assertTrue(invoiceLine.getId() > 0);
    }


    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceInvoiceNullFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        billingService.createInvoiceLine(null, medidataService);

        Assert.fail();
    }

    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceMedidataServiceNullFail() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = billingService.createInvoice(customer, currency);
        billingService.createInvoiceLine(invoice, (MedidataService)null);

        Assert.fail();
    }


    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceCurrencyDifferentFail() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency1 = billingService.createCurrency("Pound Sterling", "£");
        Currency currency2 = billingService.createCurrency("Pound Sterling 1", "£");
        Invoice invoice = billingService.createInvoice(customer, currency1);
        MedidataService medidataService = billingService.createService("Service 1", currency2, 100.10);
        billingService.createInvoiceLine(invoice, medidataService);

        Assert.fail();
    }



    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceInvoiceIdZeroFail() throws BillingServiceException, NoSuchFieldException, IllegalAccessException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);

        billingService.createInvoiceLine(invoice, medidataService);


        Assert.fail();
    }



    @Test
    public void testInvoiceLineWithServiceProduct() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = billingService.createInvoice(customer, currency);
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = billingService.createServiceProduct("Name", currency, 100.10, medidataService);

        InvoiceLine invoiceLineService = billingService.createInvoiceLine(invoice, medidataService);

        InvoiceLine invoiceLineProduct = billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.assertTrue(invoiceLineProduct.getId() > 0);
    }

    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceProductInvoiceNullFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = billingService.createServiceProduct("Name", currency, 100.10, medidataService);
        billingService.createInvoiceLine(null, medidataServiceProduct);

        Assert.fail();
    }

    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceProductFail() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Invoice invoice = billingService.createInvoice(customer, currency);
        billingService.createInvoiceLine(invoice, (MedidataServiceProduct)null);

        Assert.fail();
    }


    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceProductCurrencyDifferentFail() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency1 = billingService.createCurrency("Pound Sterling 1", "£");
        Currency currency2 = billingService.createCurrency("Pound Sterling 2", "£");
        Invoice invoice = billingService.createInvoice(customer, currency1);
        MedidataService medidataService = billingService.createService("Service 1", currency2, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = billingService.createServiceProduct("Name", currency2, 100.10, medidataService);
        billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.fail();
    }


    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceProductInvoiceIdZero() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = new Invoice(customer, currency, LocalDateTime.now());
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = billingService.createServiceProduct("Name", currency, 100.10, medidataService);
        billingService.createInvoiceLine(invoice, medidataService);
        billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.fail();
    }



    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceProductMedidataProductIdZero() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = billingService.createInvoice(customer, currency);
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = new MedidataServiceProduct("Name", currency, new BigDecimal(100.10), medidataService);
        billingService.createInvoiceLine(invoice, medidataService);
        billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.fail();
    }




    @Test(expected = BillingServiceException.class)
    public void testInvoiceLineWithServiceProductNoService() throws BillingServiceException {
        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        Currency currency = billingService.createCurrency("Pound Sterling", "£");
        Invoice invoice = billingService.createInvoice(customer, currency);
        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        MedidataServiceProduct medidataServiceProduct
                = billingService.createServiceProduct("Name", currency, 100.10, medidataService);
       // billingService.createInvoiceLine(invoice, medidataService);
        billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.fail();
    }






    @Test
    public void testAllCustomers() throws BillingServiceException {
        billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        billingService.createCustomer(Title.MR, "Johny", "Osbourne", LocalDate.now(), "johny.osbourne@gmail.com");

        List<Customer> customerList = billingService.fetchAllCustomers();

        Assert.assertEquals(customerList.size(), 2);
    }


    @Test
    public void testAllCustomersWithInsurance() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Name", "Details", 100.10, medidataService, 15);

        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        customer.addInsurance(insurance);

        billingService.update(customer);

        billingService.createCustomer(Title.MR, "Johny", "Osbourne", LocalDate.now(), "johny.osbourne@gmail.com");

        List<Customer> customerList = billingService.fetchAllCustomersWithInsurance();

        Assert.assertEquals(customerList.size(), 1);
    }


    @Test
    public void testAllCustomersWithInsuranceWithInsuranceName() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Insurance Name", "Details", 100.10, medidataService, 15);

        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com");
        customer.addInsurance(insurance);

        billingService.update(customer);

        Customer customer2 = billingService.createCustomer(Title.MR, "Jerry", "Osbourne", LocalDate.now(), "jerry.osbourne@gmail.com");
        customer2.addInsurance(insurance);

        billingService.update(customer2);


        List<Customer> customerList = billingService.fetchAllCustomersWithInsurance("insurance name");

        Assert.assertEquals(customerList.size(), 2);
    }



    @Test
    public void testCustomerTotal() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Insurance Name", "Details", 100.10, medidataService, 15);

        billingService.createDiscount(65, 70, 60);
        LocalDate customerDOB = LocalDate.now().minusYears(67);

        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", customerDOB, "harry.osbourne@gmail.com");
        customer.addInsurance(insurance);

        customer = billingService.update(customer);

        Invoice invoice = billingService.createInvoice(customer, currency);

        MedidataServiceProduct medidataServiceProduct = billingService.createServiceProduct("Name", currency, 100.10, medidataService);

        billingService.createInvoiceLine(invoice, medidataService);

        billingService.createInvoiceLine(invoice, medidataServiceProduct);
        billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.assertEquals(invoice.calculateTotalToTwoDecimal(), "105.10");
    }


    @Test
    public void testCustomerTotalAfterInvoiceLineRemoved() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Insurance Name", "Details", 100.10, medidataService, 15);

        billingService.createDiscount(65, 70, 60);
        LocalDate customerDOB = LocalDate.now().minusYears(67);

        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", customerDOB, "harry.osbourne@gmail.com");
        customer.addInsurance(insurance);

        customer = billingService.update(customer);

        Invoice invoice = billingService.createInvoice(customer, currency);

        MedidataServiceProduct medidataServiceProduct = billingService.createServiceProduct("Name", currency, 100.10, medidataService);

        billingService.createInvoiceLine(invoice, medidataService);

        InvoiceLine invoiceLine1 = billingService.createInvoiceLine(invoice, medidataServiceProduct);
        InvoiceLine invoiceLine2 = billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.assertEquals(invoice.calculateTotalToTwoDecimal(), "105.10");

        billingService.removeInvoiceLine(invoiceLine2);

        Assert.assertEquals(invoice.calculateTotalToTwoDecimal(), "65.06");
    }





    @Test(expected = BillingServiceException.class)
    public void testRemovingInvoiceLineThatHasServiceForAProduct() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Insurance Name", "Details", 100.10, medidataService, 15);

        billingService.createDiscount(65, 70, 60);
        LocalDate customerDOB = LocalDate.now().minusYears(67);

        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", customerDOB, "harry.osbourne@gmail.com");
        customer.addInsurance(insurance);

        customer = billingService.update(customer);

        Invoice invoice = billingService.createInvoice(customer, currency);

        MedidataServiceProduct medidataServiceProduct = billingService.createServiceProduct("Name", currency, 100.10, medidataService);

        InvoiceLine invoiceLineService = billingService.createInvoiceLine(invoice, medidataService);
        InvoiceLine invoiceLineServiceProduct = billingService.createInvoiceLine(invoice, medidataServiceProduct);

        Assert.assertEquals(invoice.calculateTotalToTwoDecimal(), "65.06");

        billingService.removeInvoiceLine(invoiceLineService);

        Assert.fail();
    }


    @Test
    public void testRemovingServiceWithoutProduct() throws BillingServiceException {
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        MedidataService medidataService = billingService.createService("Service 1", currency, 100.10);
        Insurance insurance = billingService.createInsurance("Insurance Name", "Details", 100.10, medidataService, 15);

        billingService.createDiscount(65, 70, 60);
        LocalDate customerDOB = LocalDate.now().minusYears(67);

        Customer customer = billingService.createCustomer(Title.MR, "Harry", "Osbourne", customerDOB, "harry.osbourne@gmail.com");
        customer.addInsurance(insurance);

        customer = billingService.update(customer);

        Invoice invoice = billingService.createInvoice(customer, currency);

        InvoiceLine invoiceLineService = billingService.createInvoiceLine(invoice, medidataService);

        billingService.removeInvoiceLine(invoiceLineService);

        Assert.assertEquals(invoice.getTotal().doubleValue(), 0, 0);
    }


    @Test(expected = BillingServiceException.class)
    public void testCustomerTotalAfterInvoiceLineRemovedInvoiceLineNull() throws BillingServiceException {
        billingService.removeInvoiceLine(null);
        Assert.fail();
    }




    @Test(expected = BillingServiceException.class)
    public void testAllCustomersWithInsuranceWithInsuranceNameNull() throws BillingServiceException {
        billingService.fetchAllCustomersWithInsurance(null);
        Assert.fail();
    }

    @Test(expected = BillingServiceException.class)
    public void testAllCustomersWithInsuranceWithInsuranceNameEmpty() throws BillingServiceException {
        billingService.fetchAllCustomersWithInsurance("");
        Assert.fail();
    }

}
