package com.itv.jms.transferable;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sakibchoudhury on 30/12/17.
 */
public class CheckoutBeginRequestTest {

    @Test
    public void testPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<CheckoutBeginRequest> constructor = CheckoutBeginRequest.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CheckoutBeginRequest checkoutBeginRequest = constructor.newInstance();

        Assert.assertNotNull(checkoutBeginRequest);
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(CheckoutBeginRequest.class)
                        .usingGetClass()
                        .suppress(Warning.NONFINAL_FIELDS)
                        .verify();
    }

    @Test
    public void testToString() {
        CheckoutBeginRequest checkoutBeginRequest1 = new CheckoutBeginRequest(1, 2, "username");
        CheckoutBeginRequest checkoutBeginRequest2 = new CheckoutBeginRequest(1, 2, "username");


        Assert.assertTrue(checkoutBeginRequest1.toString().equals(checkoutBeginRequest2.toString()));

    }
}
