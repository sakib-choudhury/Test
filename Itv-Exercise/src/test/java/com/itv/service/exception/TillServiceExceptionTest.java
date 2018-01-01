package com.itv.service.exception;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 31/12/17.
 */
public class TillServiceExceptionTest {

    @Test
    public void testConstructorWithStringMessage() {
        TillServiceException tillServiceException = new TillServiceException("test");

        Assert.assertEquals(tillServiceException.getMessage(), "test");
    }
}
