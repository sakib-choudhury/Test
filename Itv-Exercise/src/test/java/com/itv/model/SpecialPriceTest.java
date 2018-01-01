package com.itv.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 20/12/17.
 */
public class SpecialPriceTest {

    @Test
    public void testConstructor() {
        SpecialPrice specialPrice = new SpecialPrice(3, new BigDecimal(130), new BigDecimal(130));

        Assert.assertEquals(specialPrice.getUnits(), 3);
        Assert.assertEquals(specialPrice.getTotalPrice(), new BigDecimal(130));
    }


    @Test
    public void testEquals() {
        EqualsVerifier.forClass(SpecialPrice.class)
                .usingGetClass()
                .verify();
    }

    @Test
    public void testToString() {
        SpecialPrice specialPrice1 = new SpecialPrice(3, new BigDecimal(130), new BigDecimal(130));
        SpecialPrice specialPrice2 = new SpecialPrice(3, new BigDecimal(130), new BigDecimal(130));

        Assert.assertTrue(specialPrice1.toString().equals(specialPrice2.toString()));
    }

}
