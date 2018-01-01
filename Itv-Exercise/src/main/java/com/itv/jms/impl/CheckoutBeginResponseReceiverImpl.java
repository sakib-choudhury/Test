package com.itv.jms.impl;

import com.itv.jms.transferable.CheckoutBeginRequest;
import com.itv.jms.transferable.CheckoutBeginResponse;
import com.itv.jms.CheckoutBeginResponseReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
@Service
public class CheckoutBeginResponseReceiverImpl implements CheckoutBeginResponseReceiver {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public CheckoutBeginResponse produceCheckoutBeginResponse(CheckoutBeginRequest checkoutBeginRequest) {
        return (CheckoutBeginResponse) jmsTemplate.receiveAndConvert(checkoutBeginRequest.productReceiverDestination());
    }
}
