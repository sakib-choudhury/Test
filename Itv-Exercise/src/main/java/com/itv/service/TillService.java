package com.itv.service;

import com.itv.model.Currency;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.model.Title;
import com.itv.service.exception.TillServiceException;

import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
public interface TillService {

    TillUser createTillUser(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email, String username, String password) throws TillServiceException;

    TillUser findTillUserByUserName(String username) throws TillServiceException;

    Till findTillByTillNo(long storeNo, int tillNumber) throws TillServiceException;

    Till createTill(long storeNo, int tillNumber, Currency currency) throws TillServiceException;

}
