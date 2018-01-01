package com.itv.service.impl;

import com.itv.model.Currency;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.model.Title;
import com.itv.repository.TillRepository;
import com.itv.repository.exception.TillRepositoryException;
import com.itv.service.TillService;
import com.itv.service.exception.TillServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
@Service
public class TillServiceImpl implements TillService {

    @Autowired
    private TillRepository tillRepository;

    @Override
    public Till createTill(long storeNo, int number, Currency currency) throws TillServiceException {
        Till till;

        try {
            till = tillRepository.createTill(storeNo, number, currency);
        } catch (TillRepositoryException e) {
            throw new TillServiceException(e);
        }

        return till;
    }



    @Override
    public TillUser createTillUser(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email, String username, String password) throws TillServiceException {

        if(password == null || password.isEmpty())
            throw new TillServiceException("Password is empty!");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        TillUser customer;

        try {
            customer = tillRepository.createTillUser(title, firstName, lastName, dateOfBirth, email, username, hashedPassword);
        } catch (TillRepositoryException e) {
            throw new TillServiceException(e);
        }

        return customer;

    }

    @Override
    public TillUser findTillUserByUserName(String username) throws TillServiceException {
        TillUser tillUser;

        try {
            tillUser = tillRepository.findTillUserByUserName(username);
        } catch (TillRepositoryException e) {
            throw new TillServiceException(e);
        }

        return tillUser;
    }

    @Override
    public Till findTillByTillNo(long storeNo, int tillNumber) throws TillServiceException {
        Till till;

        try {
            till = tillRepository.findTillByTillNo(storeNo, tillNumber);
        } catch (TillRepositoryException e) {
            throw new TillServiceException(e);
        }

        return till;
    }
}
