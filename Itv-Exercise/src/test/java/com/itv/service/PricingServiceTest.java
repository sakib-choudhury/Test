package com.itv.service;

import com.itv.model.*;
import com.itv.service.exception.PricingServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class PricingServiceTest {

    @Autowired
    private PricingService pricingService;

    @Test
    public void testCreateCurrency() throws PricingServiceException {
        Currency currency = pricingService.createCurrency("Pound Sterling", "£");
        Assert.assertTrue(currency.getId() > 0);
    }

    @Test(expected = PricingServiceException.class)
    public void testCreateCurrencyFail() throws PricingServiceException {
        pricingService.createCurrency(null, "£");
        Assert.fail();
    }


    @Test
    public void testCreateProductWithoutSpecialPrice() throws PricingServiceException {
        Currency currency = pricingService.createCurrency("Pound Sterling", "£");
        Product product = pricingService.createProduct("A", currency, 100.10);

        Assert.assertTrue(product.getId() > 0);
    }

    @Test(expected = PricingServiceException.class)
    public void testCreateProductWithoutSpecialPriceFail() throws PricingServiceException {
        Currency currency = pricingService.createCurrency("Pound Sterling", "£");
        pricingService.createProduct(null, currency, 100.10);

        Assert.fail();
    }



}
