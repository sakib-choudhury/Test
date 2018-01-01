package com.itv.repository;

import com.itv.model.Currency;
import com.itv.model.Product;
import com.itv.repository.exception.PricingRepositoryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class PricingRepositoryTest {

    @Autowired
    private PricingRepository pricingRepository;

    @Test
    public void testFetchAllProducts() throws PricingRepositoryException {
        Currency currency = pricingRepository.createCurrency("Pound Sterling", "£");
        List<Product> productList =  pricingRepository.fetchAllProducts(currency);

        Assert.assertEquals(productList, new ArrayList<Product>());
    }

    @Test(expected = Exception.class)
    public void testCreateCurrencyFail() throws PricingRepositoryException {
        pricingRepository.createCurrency(null, "£");

        Assert.fail();
    }

    @Test(expected = Exception.class)
    public void testCreateProductFail() throws PricingRepositoryException {
        Currency currency = pricingRepository.createCurrency("Pound Sterling", "£");
        pricingRepository.createProduct(null, currency, new BigDecimal(50));

        Assert.fail();
    }



}
