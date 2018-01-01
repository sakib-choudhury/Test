package com.itv.jms;

import com.itv.jms.transferable.CheckoutBeginRequest;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
public interface CheckoutBeginRequestSender {
    void sendRequest(CheckoutBeginRequest checkoutBeginRequest);
}
