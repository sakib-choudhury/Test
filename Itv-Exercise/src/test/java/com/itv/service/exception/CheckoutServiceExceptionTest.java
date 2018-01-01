package com.itv.service.exception;

import com.itv.service.exception.CheckoutServiceException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
public class CheckoutServiceExceptionTest {

    @Test
    public void constructorTest() {
        NullPointerException nullPointerException = new NullPointerException();
        CheckoutServiceException checkoutServiceException = new CheckoutServiceException("message", nullPointerException);

        Assert.assertEquals(checkoutServiceException.getMessage(), "message");
        Assert.assertEquals(checkoutServiceException.getCause(), nullPointerException);

    }

}
