package com.itv.model;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 07/12/17.
 */
public class TitleTest {

    @Test
    public void testMr() {
        Assert.assertEquals(Title.MR.getTitleValue(), "Mr");
    }

    @Test
    public void testMrs() {
        Assert.assertEquals(Title.MRS.getTitleValue(), "Mrs");
    }

    @Test
    public void testMs() {
        Assert.assertEquals(Title.MS.getTitleValue(), "Ms");
    }
}
