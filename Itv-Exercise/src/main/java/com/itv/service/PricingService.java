package com.itv.service;

import com.itv.model.*;
import com.itv.service.exception.PricingServiceException;

import java.util.List;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
public interface PricingService {
    Currency createCurrency(String name, String iso) throws PricingServiceException;

    Product createProduct(String sku, Currency currency, double price) throws PricingServiceException;

    List<Product> fetchProductList(Currency currency);

    Product update(Product productA);
}
