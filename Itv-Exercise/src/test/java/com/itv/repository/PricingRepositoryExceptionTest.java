package com.itv.repository;

import com.itv.repository.exception.PricingRepositoryException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 25/12/17.
 */
public class PricingRepositoryExceptionTest {

    @Test
    public void testConstructor() {
        NullPointerException nullPointerException = new NullPointerException();
        PricingRepositoryException pricingRepositoryException = new PricingRepositoryException(nullPointerException);

        Assert.assertEquals(pricingRepositoryException.getCause(), nullPointerException);
    }
}
