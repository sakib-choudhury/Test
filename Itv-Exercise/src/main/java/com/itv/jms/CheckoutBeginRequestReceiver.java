package com.itv.jms;

import com.itv.jms.transferable.CheckoutBeginRequest;
import org.springframework.jms.annotation.JmsListener;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
public interface CheckoutBeginRequestReceiver {
    @JmsListener(destination = CheckoutBeginQueueDestination.REQUEST)
    void receiveMessageAndSendToSpecificDestination(CheckoutBeginRequest checkoutBeginRequest);
}
