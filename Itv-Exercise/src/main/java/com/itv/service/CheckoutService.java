package com.itv.service;

import com.itv.model.Checkout;
import com.itv.service.exception.CheckoutServiceException;
import com.itv.service.exception.CheckoutServiceInitiationException;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
public interface CheckoutService {

    void startCheckout(long storeNo, int tillNo, String tillUserName) throws CheckoutServiceException;

    void scanProduct(String productSku) throws CheckoutServiceInitiationException;

    Checkout finishCheckout() throws CheckoutServiceInitiationException;
}
