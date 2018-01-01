package com.medidata.service.impl;

/**
 * Created by sakibchoudhury on 04/12/17.
 */
public class BillingServiceException extends Exception {
    public BillingServiceException(Throwable cause) {
        super(cause);
    }

    public BillingServiceException(String message) {
        super(message);
    }
}
