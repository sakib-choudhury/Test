package com.itv.converter;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sakibchoudhury on 31/12/17.
 */
public class CustomObjectMapperTest {

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<CustomObjectMapper> customObjectMapperConstructor = CustomObjectMapper.class.getDeclaredConstructor();
        customObjectMapperConstructor.setAccessible(true);
        CustomObjectMapper customObjectMapper = customObjectMapperConstructor.newInstance();

        Assert.assertNotNull(customObjectMapper);
    }
}
