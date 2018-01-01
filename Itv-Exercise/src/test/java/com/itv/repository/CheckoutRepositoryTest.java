package com.itv.repository;

import com.itv.model.*;
import com.itv.repository.exception.CheckoutRepositoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 30/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class CheckoutRepositoryTest {

    @Autowired
    private CheckoutRepository checkoutRepository;


    @Test(expected = CheckoutRepositoryException.class)
    public void testFetchAllProductsAndDiscountsNotFound() throws CheckoutRepositoryException, NoSuchFieldException, IllegalAccessException {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();

        Checkout checkout = new Checkout(till, tillUser, new BigDecimal(10), new BigDecimal(100), startTime, endTime);

        Field idField = Checkout.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(checkout, 1);

        checkoutRepository.fetchAllProductsAndDiscounts(checkout);

    }
}
