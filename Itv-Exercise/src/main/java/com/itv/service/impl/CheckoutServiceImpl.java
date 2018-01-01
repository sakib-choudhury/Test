package com.itv.service.impl;

import com.itv.jms.transferable.CheckoutBeginRequest;
import com.itv.jms.CheckoutBeginRequestSender;
import com.itv.jms.transferable.CheckoutBeginResponse;
import com.itv.jms.CheckoutBeginResponseReceiver;
import com.itv.model.Checkout;
import com.itv.model.Product;
import com.itv.model.ScannedProduct;
import com.itv.model.ScannedProductDiscount;
import com.itv.repository.CheckoutRepository;
import com.itv.repository.exception.CheckoutRepositoryException;
import com.itv.service.CheckoutService;
import com.itv.service.exception.CheckoutServiceException;
import com.itv.service.exception.CheckoutServiceInitiationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger logger = Logger.getLogger(CheckoutService.class);

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CheckoutBeginRequestSender checkoutBeginRequestSender;

    @Autowired
    private CheckoutBeginResponseReceiver checkoutBeginResponseReceiver;


    private CheckoutBeginResponse checkoutBeginResponse;
    private List<Product> productList;
    private LocalDateTime checkoutStartTime;
    private List<ScannedProduct> scannedProductList;

    //sku vs items
    private Map<String, Integer> skuQuantityMap;

    //sku vs items
    private Map<String, Product> skuProductMap;

    private boolean checkoutStarted = false;


    @Override
    public void startCheckout(long storeNo, int tillNo, String tillUserName) throws CheckoutServiceException {

        logger.info("START startCheckout Till No: " + tillNo + " Till Username: " + tillUserName);

        CheckoutBeginRequest checkoutBeginRequest = new CheckoutBeginRequest(storeNo, tillNo, tillUserName);

        checkoutBeginRequestSender.sendRequest(checkoutBeginRequest);

        checkoutBeginResponse = checkoutBeginResponseReceiver.produceCheckoutBeginResponse(checkoutBeginRequest);



        if(checkoutBeginResponse.getTillUser() == null) {
            throw new CheckoutServiceException("Till User is empty!", checkoutBeginResponse.getException());
        }

        if(checkoutBeginResponse.getTill() == null) {
            throw new CheckoutServiceException("Till is empty!", checkoutBeginResponse.getException());
        }

        if(checkoutBeginResponse.getProductList().size() == 0 ) {
            throw new CheckoutServiceException("Product List is empty!", checkoutBeginResponse.getException());
        }

        productList = checkoutBeginResponse.getProductList();
        checkoutStartTime = LocalDateTime.now();

        checkoutStarted = true;

        initPrivateCollections();

    }

    private void initPrivateCollections() {
        scannedProductList = new ArrayList<>();
        skuQuantityMap = new HashMap<>();
        skuProductMap = new HashMap<>();
    }


    @Override
    public void scanProduct(String productSku) throws CheckoutServiceInitiationException {

        if(!checkoutStarted)
            throw new CheckoutServiceInitiationException("Please Start the Checkout First!");

        if(skuQuantityMap.containsKey(productSku)) {
            skuQuantityMap.put(productSku, skuQuantityMap.get(productSku) + 1);

            Product product = skuProductMap.get(productSku);
            ScannedProduct scannedProduct = new ScannedProduct(LocalDateTime.now(), productSku, product.getCurrency(), product.getPrice());
            scannedProductList.add(scannedProduct);

            return;
        } else {
            for(Product product : productList) {
                if(!product.getSku().equals(productSku))
                    continue;

                skuQuantityMap.put(productSku, 1);
                skuProductMap.put(productSku, product);

                ScannedProduct scannedProduct = new ScannedProduct(LocalDateTime.now(), productSku, product.getCurrency(), product.getPrice());
                scannedProductList.add(scannedProduct);

            }
        }

        if(!skuQuantityMap.containsKey(productSku))
            throw new IllegalArgumentException("Product SKU does not exist!");

    }


    @Override
    public Checkout finishCheckout() throws CheckoutServiceInitiationException {

        if(!checkoutStarted)
            throw new CheckoutServiceInitiationException("Please Start the Checkout First!");

        LocalDateTime checkoutEndTime = LocalDateTime.now();

        List<ScannedProductDiscount> scannedProductDiscountList = new ArrayList<>();
        BigDecimal subTotal = new BigDecimal(0);
        BigDecimal savings = new BigDecimal(0);

        for(String productSku : skuQuantityMap.keySet()) {
            int totalQuantity = skuQuantityMap.get(productSku);

            Product product = skuProductMap.get(productSku);
            subTotal = subTotal.add(product.getPrice().multiply(new BigDecimal(totalQuantity)));

            int specialPriceQuantity = product.getSpecialPrice().getUnits();

            int numberOfDiscounts = (totalQuantity / specialPriceQuantity);

            savings = savings.add(product.getSpecialPrice().getSavings().multiply(new BigDecimal(numberOfDiscounts)));

            for(int i = 0; i< numberOfDiscounts; i++) {
                ScannedProductDiscount scannedProductDiscount = new ScannedProductDiscount(productSku, product.getCurrency(), product.getSpecialPrice().getSavings());
                scannedProductDiscountList.add(scannedProductDiscount);
            }

        }


        BigDecimal total = subTotal.subtract(savings);

        Checkout checkout = checkoutRepository.createCheckout(checkoutBeginResponse.getTill(),
                                                                checkoutBeginResponse.getTillUser(),
                                                                subTotal,
                                                                total,
                                                                checkoutStartTime,
                                                                checkoutEndTime);

        for(ScannedProduct scannedProduct : scannedProductList) {
            scannedProduct.setCheckout(checkout);
        }

        for(ScannedProductDiscount scannedProductDiscount : scannedProductDiscountList) {
            scannedProductDiscount.setCheckout(checkout);
        }

        List<ScannedProduct> insertedScannedProductList = checkoutRepository.batchInsert(scannedProductList);
        checkout.setScannedProductSet(new LinkedHashSet<>(insertedScannedProductList));
        checkout = checkoutRepository.update(checkout);

        List<ScannedProductDiscount> insertedScannedProductDiscount = checkoutRepository.batchInsert(scannedProductDiscountList);
        checkout.setScannedProductDiscountSet(new LinkedHashSet<>(insertedScannedProductDiscount));
        checkout = checkoutRepository.update(checkout);

        try {
            checkout = checkoutRepository.fetchAllProductsAndDiscounts(checkout);
        } catch (CheckoutRepositoryException e) {
            //should not happen
            throw new IllegalStateException("Checkout could not be fetched!");
        }


        checkoutBeginResponse = null;
        initPrivateCollections();

        checkoutStarted = false;

        return checkout;
    }

}
