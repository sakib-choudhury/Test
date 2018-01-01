package com.itv.repository;

import com.itv.model.Currency;
import com.itv.model.Product;
import com.itv.repository.exception.PricingRepositoryException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
public interface PricingRepository extends GenericRepository {

    Currency createCurrency(String name, String iso) throws PricingRepositoryException;

    Product createProduct(String sku, Currency currency, BigDecimal price) throws PricingRepositoryException;

    List<Product> fetchAllProducts(Currency currency);
}
