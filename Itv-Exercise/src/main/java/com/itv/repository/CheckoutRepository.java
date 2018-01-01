package com.itv.repository;

import com.itv.model.Checkout;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.repository.exception.CheckoutRepositoryException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
public interface CheckoutRepository extends GenericRepository{

    Checkout fetchAllProductsAndDiscounts(Checkout checkout) throws CheckoutRepositoryException;

    Checkout createCheckout(Till till, TillUser tillUser, BigDecimal subTotal, BigDecimal total, LocalDateTime checkoutStartTime, LocalDateTime checkoutEndTime);
}
