package com.itv.service.impl;

import com.itv.model.Currency;
import com.itv.model.Product;
import com.itv.repository.PricingRepository;
import com.itv.repository.exception.PricingRepositoryException;
import com.itv.service.PricingService;
import com.itv.service.exception.PricingServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Service
public class PricingServiceImpl implements PricingService{

    @Autowired
    private PricingRepository pricingRepository;


    @Override
    public Currency createCurrency(String name, String iso) throws PricingServiceException {
        Currency currency;
        try {
            currency = pricingRepository.createCurrency(name, iso);
        } catch (PricingRepositoryException e) {
            throw new PricingServiceException(e);
        }
        return currency;
    }



    @Override
    public Product createProduct(String sku, Currency currency, double price) throws PricingServiceException {
        Product product;

        try {
            product = pricingRepository.createProduct(sku, currency, new BigDecimal(price));
        } catch (PricingRepositoryException e) {
            throw new PricingServiceException(e);
        }

        return product;
    }




    @Override
    public List<Product> fetchProductList(Currency currency) {
        return pricingRepository.fetchAllProducts(currency);
    }

    @Override
    public Product update(Product productA) {
        return pricingRepository.update(productA);
    }


}
