package com.itv.repository;

import com.itv.repository.exception.TillRepositoryException;
import com.itv.repository.impl.TillRepositoryImpl;
import org.junit.Test;

/**
 * Created by sakibchoudhury on 31/12/17.
 */
public class TillRepositoryTest {

    @Test(expected = TillRepositoryException.class)
    public void testCreateTill() throws TillRepositoryException {
        TillRepositoryImpl tillRepository = new TillRepositoryImpl();

        tillRepository.createTill(0, 0, null);
    }

    @Test(expected = TillRepositoryException.class)
    public void testCreateTillUser() throws TillRepositoryException {
        TillRepositoryImpl tillRepository = new TillRepositoryImpl();

        tillRepository.createTillUser(null, null, null, null, null, null, null);
    }
}
