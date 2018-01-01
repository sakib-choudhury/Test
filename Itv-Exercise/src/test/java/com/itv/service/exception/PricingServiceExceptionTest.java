package com.itv.service.exception;

import com.itv.service.exception.PricingServiceException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 25/12/17.
 */
public class PricingServiceExceptionTest {

    @Test
    public void testConstructor() {
        NullPointerException nullPointerException = new NullPointerException();
        PricingServiceException pricingServiceException = new PricingServiceException(nullPointerException);

        Assert.assertEquals(pricingServiceException.getCause(), nullPointerException);
    }
}
