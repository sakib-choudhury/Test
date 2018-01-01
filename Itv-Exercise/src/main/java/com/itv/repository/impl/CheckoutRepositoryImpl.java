package com.itv.repository.impl;

import com.itv.model.Checkout;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.repository.CheckoutRepository;
import com.itv.repository.exception.CheckoutRepositoryException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Repository
@Transactional
public class CheckoutRepositoryImpl extends GenericRepositoryImpl implements CheckoutRepository {

    @Override
    public Checkout fetchAllProductsAndDiscounts(Checkout checkout) throws CheckoutRepositoryException {
        //language=HQL
        String queryString = "select checkout " +
                             " from Checkout checkout " +
                             " left join fetch checkout.scannedProductSet " +
                             " left join fetch checkout.scannedProductDiscountSet " +
                             " where checkout = :checkout " ;

        Checkout foundCheckout;

        try {
            foundCheckout = entityManager.createQuery(queryString, Checkout.class)
                                    .setParameter("checkout", checkout)
                                    .getSingleResult();
        } catch (Exception e) {
            throw new CheckoutRepositoryException(e);
        }

        return foundCheckout;
    }

    @Override
    public Checkout createCheckout(Till till, TillUser tillUser, BigDecimal subTotal, BigDecimal total, LocalDateTime checkoutStartTime, LocalDateTime checkoutEndTime) {
        Checkout checkout = new Checkout(till, tillUser, subTotal,
                                         total, checkoutStartTime, checkoutEndTime);

        return super.insert(checkout);
    }
}
