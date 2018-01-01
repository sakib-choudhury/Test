package com.itv.repository.impl;

import com.itv.model.Currency;
import com.itv.model.Product;
import com.itv.repository.PricingRepository;
import com.itv.repository.exception.PricingRepositoryException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

//import org.apache.log4j.Logger;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Repository
@Transactional
public class PricingRepositoryImpl extends GenericRepositoryImpl implements PricingRepository {
    //private static final Logger logger = Logger.getLogger(PricingRepositoryImpl.class);

    @Override
    public Currency createCurrency(String name, String iso) throws PricingRepositoryException {

        Currency currency;
        try {
            currency = new Currency(name, iso);
        } catch (Exception e) {
            throw new PricingRepositoryException(e);
        }

        return super.insert(currency);
    }


    @Override
    public Product createProduct(String sku, Currency currency, BigDecimal price) throws PricingRepositoryException {
        Product product;

        try {
            product = new Product(sku, currency, price);
        } catch (Exception e) {
            throw new PricingRepositoryException(e);
        }


        return super.insert(product);
    }


    @Override
    public List<Product> fetchAllProducts(Currency currency) {

        //language=HQL
        String queryString = "select product " +
                                "from Product product " +
                                "order by product.sku asc  ";

        List<Product> resultList = entityManager.createQuery(queryString).getResultList();

        return resultList;
    }






}
