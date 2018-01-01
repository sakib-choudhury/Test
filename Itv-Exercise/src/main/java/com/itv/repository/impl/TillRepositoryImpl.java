package com.itv.repository.impl;

import com.itv.model.Currency;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.model.Title;
import com.itv.repository.TillRepository;
import com.itv.repository.exception.TillRepositoryException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
@Repository
@Transactional
public class TillRepositoryImpl extends GenericRepositoryImpl implements TillRepository {

    @Override
    public Till createTill(long storeNo, int number, Currency currency) throws TillRepositoryException {
        Till till;
        try {
            till = new Till(storeNo, number, currency);
        } catch (Exception e) {
            throw new TillRepositoryException(e);
        }


        return super.insert(till);
    }

    @Override
    public TillUser createTillUser(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email, String username, String password) throws TillRepositoryException {
        TillUser tillUser;
        try {
            tillUser = new TillUser(title, firstName, lastName, dateOfBirth, email, username, password);
        } catch (Exception e) {
            throw new TillRepositoryException(e);
        }

        return super.insert(tillUser);
    }

    @Override
    public TillUser findTillUserByUserName(String username) throws TillRepositoryException {

        //language=HQL
        String queryString = "select tillUser " +
                "from TillUser tillUser " +
                "where tillUser.username = :username  ";

        TillUser tillUser;

        try {
            tillUser = entityManager.createQuery(queryString, TillUser.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            throw new TillRepositoryException(e);
        }

        return tillUser;
    }

    @Override
    public Till findTillByTillNo(long storeNo, int tillNumber) throws TillRepositoryException {
        //language=HQL
        String queryString = "select till " +
                "from Till till " +
                "where till.storeNo = :storeNo and till.tillNumber = :tillNumber  ";

        Till till;

        try {
            till = entityManager.createQuery(queryString, Till.class)
                    .setParameter("storeNo", storeNo)
                    .setParameter("tillNumber", tillNumber)
                    .getSingleResult();
        } catch (Exception e) {
            throw new TillRepositoryException(e);
        }

        return till;
    }


}
