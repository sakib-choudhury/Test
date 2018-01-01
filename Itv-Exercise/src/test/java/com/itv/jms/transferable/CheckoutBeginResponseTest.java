package com.itv.jms.transferable;

import com.itv.model.Currency;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.model.Title;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by sakibchoudhury on 30/12/17.
 */
public class CheckoutBeginResponseTest {

    @Test
    public void testPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<CheckoutBeginResponse> constructor = CheckoutBeginResponse.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CheckoutBeginResponse checkoutBeginResponse = constructor.newInstance();

        Assert.assertNotNull(checkoutBeginResponse);
    }

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(CheckoutBeginResponse.class)
                .usingGetClass()
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }


    @Test
    public void testToString() {
        TillUser tillUser = new TillUser(Title.MR, "Harry", "Osbourne", LocalDate.now(), "harry.osbourne@gmail.com", "username", "password");
        Currency currency = new Currency("Pound Sterling", "£");
        Till till = new Till(10, 19, currency);

        CheckoutBeginResponse checkoutBeginResponse1 = new CheckoutBeginResponse(tillUser, till, new ArrayList<>(), null);
        CheckoutBeginResponse checkoutBeginResponse2 = new CheckoutBeginResponse(tillUser, till, new ArrayList<>(), null);


        Assert.assertTrue(checkoutBeginResponse1.toString().equals(checkoutBeginResponse2.toString()));

    }
}
