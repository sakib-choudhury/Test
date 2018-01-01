package com.itv.jms.impl;

import com.itv.jms.CheckoutBeginQueueDestination;
import com.itv.jms.transferable.CheckoutBeginRequest;
import com.itv.jms.CheckoutBeginRequestReceiver;
import com.itv.jms.transferable.CheckoutBeginResponse;
import com.itv.model.Product;
import com.itv.model.Till;
import com.itv.model.TillUser;
import com.itv.service.PricingService;
import com.itv.service.TillService;
import com.itv.service.exception.TillServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
@Service
public class CheckoutBeginRequestReceiverImpl implements CheckoutBeginRequestReceiver {

    private static final Logger logger = Logger.getLogger(CheckoutBeginRequestReceiverImpl.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private PricingService pricingService;

    @Autowired
    private TillService tillService;

    @Override
    @JmsListener(destination = CheckoutBeginQueueDestination.REQUEST)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void receiveMessageAndSendToSpecificDestination(CheckoutBeginRequest checkoutBeginRequest) {
        logger.info("Execute method asynchronously. "+ Thread.currentThread().getName());

        TillUser tillUser;
        try {
            tillUser = tillService.findTillUserByUserName(checkoutBeginRequest.getTillUserName());
        } catch (TillServiceException e) {
            jmsTemplate.convertAndSend(checkoutBeginRequest.productReceiverDestination(), new CheckoutBeginResponse(null, null, null, e));
            return;
        }


        Till till;

        try {
            till = tillService.findTillByTillNo(checkoutBeginRequest.getStoreNo(), checkoutBeginRequest.getTillNo());
        } catch (TillServiceException e) {
            jmsTemplate.convertAndSend(checkoutBeginRequest.productReceiverDestination(), new CheckoutBeginResponse(tillUser, null, null, e));
            return;
        }


        List<Product> productList = pricingService.fetchProductList(till.getCurrency());

        jmsTemplate.convertAndSend(checkoutBeginRequest.productReceiverDestination(), new CheckoutBeginResponse(tillUser, till, productList, null));
    }
}
