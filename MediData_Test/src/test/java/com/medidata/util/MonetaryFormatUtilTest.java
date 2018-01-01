package com.medidata.util;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 06/12/17.
 */
public class MonetaryFormatUtilTest {

    @Test
    public void testConstructor() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor<MoneteryFormatUtil> constructor = MoneteryFormatUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MoneteryFormatUtil moneteryFormatUtil = constructor.newInstance();

        Assert.assertNotNull(moneteryFormatUtil);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductFormattedMoneyByPatternMoneyNull() {
        MoneteryFormatUtil.productFormattedMoneyByPattern(null, "asd");

        Assert.fail();
    }

    @Test
    public void testProductFormattedMoneyByPatternMoneyZero() {
        String result = MoneteryFormatUtil.productFormattedMoneyByPattern(new BigDecimal(0), "asd");

        Assert.assertEquals(result, "0.00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductFormattedMoneyByPatternNull() {
        MoneteryFormatUtil.productFormattedMoneyByPattern(new BigDecimal(100), null);

        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductFormattedMoneyByPatternEmpty() {
        MoneteryFormatUtil.productFormattedMoneyByPattern(new BigDecimal(100), "");
        Assert.fail();
    }

    @Test
    public void testProductFormattedMoneyByPattern() {
        String result = MoneteryFormatUtil.productFormattedMoneyByPattern(new BigDecimal(100), MoneteryFormatUtil.TWO_DECIMAL_PATTERN);
        Assert.assertEquals(result, "100.00");
    }
}
