package com.itv.service;

import com.itv.model.Checkout;
import com.itv.service.exception.CheckoutServiceException;
import com.itv.service.exception.CheckoutServiceInitiationException;
import com.itv.service.exception.PricingServiceException;
import com.itv.service.exception.TillServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;


/**
 * Created by sakibchoudhury on 22/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CheckoutServiceImplTest {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private DataProviderUtil dataProviderUtil;

    @After
    public void afterTest() {
        System.out.println("CLEANING DATA...");
        dataProviderUtil.cleanData();
    }


    @Test
    public void testSweetScenario() throws PricingServiceException, CheckoutServiceException, TillServiceException, CheckoutServiceInitiationException {

        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 3, "sakibc");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("A");

        Checkout checkout = checkoutService.finishCheckout();

        Assert.assertTrue(checkout.getTotal().compareTo(new BigDecimal(130.00)) == 0);
        Assert.assertTrue(checkout.getSubTotal().compareTo(new BigDecimal(150.00)) == 0);
        Assert.assertEquals(checkout.getScannedProductSet().size(), 3);
        Assert.assertEquals(checkout.getScannedProductDiscountSet().size(), 1);

        /*TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = platformTransactionManager.getTransaction(txDef);
        platformTransactionManager.rollback(txStatus);*/

    }



    @Test
    public void testSweetScenarioMultipleProducts() throws PricingServiceException, CheckoutServiceException, TillServiceException, CheckoutServiceInitiationException {

        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 3, "sakibc");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("B");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("B");

        Checkout checkout = checkoutService.finishCheckout();

        Assert.assertTrue(checkout.getTotal().compareTo(new BigDecimal(175)) == 0);
        Assert.assertTrue(checkout.getSubTotal().compareTo(new BigDecimal(210)) == 0);
        Assert.assertEquals(checkout.getScannedProductDiscountSet().size(), 2);
        Assert.assertEquals(checkout.getScannedProductSet().size(), 5);

    }

    @Test(expected = CheckoutServiceException.class)
    public void testStartCheckoutTillNull() throws CheckoutServiceException, PricingServiceException, TillServiceException {
        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 7, "sakibc");

    }

    @Test(expected = CheckoutServiceException.class)
    public void testStartCheckoutTillUserNull() throws CheckoutServiceException, PricingServiceException, TillServiceException {

        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 3, "unknown");

    }

    @Test(expected = CheckoutServiceException.class)
    public void testStartCheckoutProductListEmpty() throws CheckoutServiceException, PricingServiceException, TillServiceException {

        dataProviderUtil.provideSampleDataWithoutProduct();

        checkoutService.startCheckout(100922, 3, "sakibc");

    }

    @Test(expected = CheckoutServiceInitiationException.class)
    public void testScanProductWithoutStartingCheckout() throws CheckoutServiceInitiationException {

        checkoutService.scanProduct("A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScanningUnknownProduct() throws PricingServiceException, CheckoutServiceException, TillServiceException, CheckoutServiceInitiationException {

        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 3, "sakibc");
        checkoutService.scanProduct("GGG");

    }

    @Test//(expected = CheckoutServiceInitiationException.class)
    public void testScanProductWithoutStartingCheckoutAndThenStartCheckoutAfterThat() throws CheckoutServiceException, PricingServiceException, TillServiceException {


        try {
            checkoutService.scanProduct("A");
        } catch (CheckoutServiceInitiationException e) {
            e.printStackTrace();
        }

        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 3, "sakibc");

    }


    @Test(expected = CheckoutServiceInitiationException.class)
    public void testFinishAndScanWithoutRestart() throws CheckoutServiceException, PricingServiceException, TillServiceException, CheckoutServiceInitiationException {

        dataProviderUtil.provideSampleData();

        checkoutService.startCheckout(100922, 3, "sakibc");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("A");
        checkoutService.scanProduct("A");

        Checkout checkout = checkoutService.finishCheckout();

        Assert.assertTrue(checkout.getTotal().compareTo(new BigDecimal(130)) == 0);
        Assert.assertTrue(checkout.getSubTotal().compareTo(new BigDecimal(150)) == 0);
        Assert.assertEquals(checkout.getScannedProductDiscountSet().size(), 1);
        Assert.assertEquals(checkout.getScannedProductSet().size(), 3);

        checkoutService.scanProduct("A");


    }

    @Test(expected = CheckoutServiceInitiationException.class)
    public void testFinishCheckoutWithoutInitiating() throws CheckoutServiceInitiationException {

        checkoutService.finishCheckout();
    }


}
