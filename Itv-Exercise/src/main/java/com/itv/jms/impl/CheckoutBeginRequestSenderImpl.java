package com.itv.jms.impl;

import com.itv.jms.CheckoutBeginQueueDestination;
import com.itv.jms.transferable.CheckoutBeginRequest;
import com.itv.jms.CheckoutBeginRequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
@Service
public class CheckoutBeginRequestSenderImpl implements CheckoutBeginRequestSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendRequest(CheckoutBeginRequest checkoutBeginRequest) {
        jmsTemplate.convertAndSend(CheckoutBeginQueueDestination.REQUEST,
                                    checkoutBeginRequest);
    }
}
