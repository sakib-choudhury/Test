package com.medidata.application;

import com.medidata.model.*;
import com.medidata.service.BillingService;
import com.medidata.service.impl.BillingServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 06/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class SampleApplication {

    @Autowired
    private BillingService billingService;

    @Test
    public void sampleApplication() throws BillingServiceException {

        /**
         * CURRENCY
         */
        Currency currency = billingService.createCurrency("Pound Sterling", "£");

        /**
         * SERVICE
         */

        MedidataService diagnosisService = billingService.createService("Diagnosis", currency, 60);
        MedidataService xrayService = billingService.createService("X-Ray", currency, 150);
        MedidataService bloodTestService = billingService.createService("Blood Test", currency, 78);
        MedidataService ecgService = billingService.createService("ECG", currency, 200.40);
        MedidataService vaccineService = billingService.createService("Vaccine", currency, 27.50);


        /**
         * PRODUCT
         */
        MedidataServiceProduct vaccineServiceProduct
                = billingService.createServiceProduct("Vaccine", currency, 15, vaccineService);


        /**
         * DISCOUNT
         */
        Discount discountBetweenSixtyFiveToSeventy = billingService.createDiscount(65, 70, 60);
        Discount discountOverSeventy = billingService.createDiscount(71, Discount.AGE_HIGHEST, 90);
        Discount discountUnderFive = billingService.createDiscount(Discount.AGE_LOWEST, 4, 40);

        /**
         * INSURANCE
         */
        Insurance insurance = billingService.createInsurance("Name", "Details", 100.10, bloodTestService, 15);


        /**
         * CUSTOMER Between 65 and 70
         */
        LocalDate customerDOBBetweenSixtyFiveAndSeventy = LocalDate.now().minusYears(67);
        Customer customerBetweenSixtyFiveToSeventy = billingService.createCustomer(Title.MR, "Harry", "Osbourne", customerDOBBetweenSixtyFiveAndSeventy, "harry.osbourne@gmail.com");

        customerBetweenSixtyFiveToSeventy.addInsurance(insurance);
        billingService.update(customerBetweenSixtyFiveToSeventy);

        Invoice invoiceForCustomerBetweenSixtyFiveToSeventy = billingService.createInvoice(customerBetweenSixtyFiveToSeventy, currency);



        InvoiceLine invoiceLineForCustomerBetweenSixtyFiveToSeventy1 = billingService.createInvoiceLine(invoiceForCustomerBetweenSixtyFiveToSeventy, diagnosisService);
        InvoiceLine invoiceLineForCustomerBetweenSixtyFiveToSeventy2 = billingService.createInvoiceLine(invoiceForCustomerBetweenSixtyFiveToSeventy, bloodTestService);

        Assert.assertEquals(invoiceForCustomerBetweenSixtyFiveToSeventy.calculateTotalToTwoDecimal(), "43.50");



        //CUSTOMER Over 70
        LocalDate customerDOBOverSeventy = LocalDate.now().minusYears(80);
        Customer customeOverSeventy = billingService.createCustomer(Title.MR, "Jerry", "Osbourne", customerDOBOverSeventy, "jerry.osbourne@gmail.com");
        customeOverSeventy.addInsurance(insurance);
        Invoice invoiceForCustomerOverSeventy = billingService.createInvoice(customeOverSeventy, currency);

        InvoiceLine invoiceLineForCustomeOverSeventy1 = billingService.createInvoiceLine(invoiceForCustomerOverSeventy, xrayService);
        InvoiceLine invoiceLineForCustomeOverSeventy2 = billingService.createInvoiceLine(invoiceForCustomerOverSeventy, vaccineService);
        InvoiceLine invoiceLineForCustomeOverSeventy3 = billingService.createInvoiceLine(invoiceForCustomerOverSeventy, vaccineServiceProduct);
        InvoiceLine invoiceLineForCustomeOverSeventy4 = billingService.createInvoiceLine(invoiceForCustomerOverSeventy, bloodTestService);


        Assert.assertEquals(invoiceForCustomerOverSeventy.calculateTotalToTwoDecimal(), "15.35");



        //CUSTOMER Under 5
        LocalDate customerDOBThreeYearsAgo = LocalDate.now().minusYears(2);
        Customer customeUnderFive = billingService.createCustomer(Title.MR, "Gary", "Osbourne", customerDOBThreeYearsAgo, "gary.osbourne@gmail.com");

        Invoice invoiceForCustomerUnderFive = billingService.createInvoice(customeUnderFive, currency);
        InvoiceLine invoiceLineForCustomerUnderFive1 = billingService.createInvoiceLine(invoiceForCustomerUnderFive, xrayService);

        Assert.assertEquals(customeUnderFive.getDiscount(), discountUnderFive);
        Assert.assertEquals(invoiceForCustomerUnderFive.calculateTotalToTwoDecimal(), "90.00");


        //CUSTOMER Over 5 less than 65

        LocalDate customerDOBFifteenYears = LocalDate.now().minusYears(15);
        Customer customeFifteenYears = billingService.createCustomer(Title.MR, "Mary", "Osbourne", customerDOBFifteenYears, "gary.osbourne@gmail.com");

        Invoice invoiceForCustomeFifteenYears = billingService.createInvoice(customeFifteenYears, currency);
        InvoiceLine invoiceLineForcustomeFifteenYears1 = billingService.createInvoiceLine(invoiceForCustomeFifteenYears, xrayService);

        Assert.assertNull(customeFifteenYears.getDiscount());
        Assert.assertEquals(invoiceForCustomeFifteenYears.calculateTotalToTwoDecimal(), "150.00");

    }

}
