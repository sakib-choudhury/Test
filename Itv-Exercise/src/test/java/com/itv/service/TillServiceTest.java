package com.itv.service;

import com.itv.model.Currency;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.model.Title;
import com.itv.service.exception.PricingServiceException;
import com.itv.service.exception.TillServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class TillServiceTest {

    @Autowired
    private TillService tillService;

    @Autowired
    private PricingService pricingService;

    @Test
    public void testCreateTillUser() throws TillServiceException {
        TillUser tillUser = tillService.createTillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", "mypassword123");

        Assert.assertTrue(tillUser.getId() > 0);
    }

    @Test
    public void testFetchTillUser() throws TillServiceException {
        TillUser tillUser = tillService.createTillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", "mypassword123");

        TillUser foundTillUser = tillService.findTillUserByUserName("sakibc");
        Assert.assertEquals(tillUser, foundTillUser);
    }


    @Test
    public void testFetchTill() throws PricingServiceException, TillServiceException {
        Currency currency = pricingService.createCurrency("Pound Sterling", "£");
        Till till = tillService.createTill(10, 3, currency);

        Till foundTill = tillService.findTillByTillNo(10, 3);
        Assert.assertEquals(till, foundTill);
    }


    @Test(expected = TillServiceException.class)
    public void testCreateTillThrowsException() throws PricingServiceException, TillServiceException {
        tillService.createTill(10, 3, null);
    }

    @Test(expected = TillServiceException.class)
    public void testCreateTillUserThrowsExceptionForPasswordNull() throws PricingServiceException, TillServiceException {
        tillService.createTillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", null);
    }

    @Test(expected = TillServiceException.class)
    public void testCreateTillUserThrowsExceptionForPasswordEmpty() throws PricingServiceException, TillServiceException {
        tillService.createTillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", "");
    }

    @Test(expected = TillServiceException.class)
    public void testCreateTillUserThrowsExceptionForTillUserPropertyNull() throws PricingServiceException, TillServiceException {
        tillService.createTillUser(null, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", "sakibc");
    }
}
