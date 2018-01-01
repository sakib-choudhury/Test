package com.itv.repository;

import com.itv.model.Currency;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.model.Title;
import com.itv.repository.exception.TillRepositoryException;

import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
public interface TillRepository extends GenericRepository {

    TillUser createTillUser(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email, String username, String password) throws TillRepositoryException;

    TillUser findTillUserByUserName(String username) throws TillRepositoryException;

    Till findTillByTillNo(long storeNo, int tillNumber) throws TillRepositoryException;

    Till createTill(long storeNo, int tillNumber, Currency currency) throws TillRepositoryException;

}

