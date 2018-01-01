package com.itv.jms;

import com.itv.jms.transferable.CheckoutBeginRequest;
import com.itv.jms.transferable.CheckoutBeginResponse;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
public interface CheckoutBeginResponseReceiver {
    CheckoutBeginResponse produceCheckoutBeginResponse(CheckoutBeginRequest checkoutBeginRequest);
}
