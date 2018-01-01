package com.itv.service;

import com.itv.model.*;
import com.itv.service.exception.PricingServiceException;
import com.itv.service.exception.TillServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 25/12/17.
 */
@Component
public class DataProviderUtil {
    @Autowired
    private TillService tillService;

    @Autowired
    private PricingService pricingService;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    //@Async
    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    @Transactional
    public void provideSampleData() throws PricingServiceException, TillServiceException {
        System.out.println("provideSampleData Execute method asynchronously. "+ Thread.currentThread().getName() );

        Currency currency = pricingService.createCurrency("Pound Sterling", "£");

        Product productA = pricingService.createProduct("A", currency, 50);
        productA.addSpecialPrice(3, 130);
        productA = pricingService.update(productA);

        Product productB = pricingService.createProduct("B", currency, 30);
        productB.addSpecialPrice(2, 45);
        productB = pricingService.update(productB);

        Product productC = pricingService.createProduct("C", currency, 20);
        Product productD = pricingService.createProduct("D", currency, 15);

        TillUser tillUser1 = tillService.createTillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", "mypassword123");
        TillUser tillUser2 = tillService.createTillUser(Title.MR, "Rakib", "Osbourne", LocalDate.now(), "rakib.osbourne@gmail.com", "rakibc", "mypassword123");
        TillUser tillUser3 = tillService.createTillUser(Title.MR, "Nakib", "Osbourne", LocalDate.now(), "nakib.osbourne@gmail.com", "nakibc", "mypassword123");

        Till till1 = tillService.createTill(100922, 1, currency);
        Till till2 = tillService.createTill(100922, 2, currency);
        Till till3 = tillService.createTill(100922, 3, currency);



    }

    @Transactional
    public void cleanData() {
        entityManager.createQuery("delete from ScannedProductDiscount ").executeUpdate();
        entityManager.createQuery("delete from ScannedProduct ").executeUpdate();
        entityManager.createQuery("delete from Checkout ").executeUpdate();
        entityManager.createQuery("delete from Till ").executeUpdate();
        entityManager.createQuery("delete from TillUser ").executeUpdate();
        entityManager.createQuery("delete from Product ").executeUpdate();
        entityManager.createQuery("delete from Currency ").executeUpdate();
    }


    public void provideSampleDataWithoutProduct() throws PricingServiceException, TillServiceException {
        Currency currency = pricingService.createCurrency("Pound Sterling", "£");

        TillUser tillUser1 = tillService.createTillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "sakibc", "mypassword123");
        TillUser tillUser2 = tillService.createTillUser(Title.MR, "Rakib", "Osbourne", LocalDate.now(), "rakib.osbourne@gmail.com", "rakibc", "mypassword123");
        TillUser tillUser3 = tillService.createTillUser(Title.MR, "Nakib", "Osbourne", LocalDate.now(), "nakib.osbourne@gmail.com", "nakibc", "mypassword123");

        Till till1 = tillService.createTill(100922, 1, currency);
        Till till2 = tillService.createTill(100922, 2, currency);
        Till till3 = tillService.createTill(100922, 3, currency);
    }


}
