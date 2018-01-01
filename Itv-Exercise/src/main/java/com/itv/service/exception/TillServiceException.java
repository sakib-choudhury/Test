package com.itv.service.exception;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
public class TillServiceException extends Exception {

    public TillServiceException(Throwable cause) {
        super(cause);
    }

    public TillServiceException(String message) {
        super(message);
    }
}
