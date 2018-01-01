package com.medidata.repository.impl;

import com.medidata.model.*;
import com.medidata.repository.BillingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sakibchoudhury on 04/12/17.
 */
@Repository
@Transactional
public class BillingRepositoryImpl implements BillingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Currency createCurrency(String name, String iso) throws BillingRepositoryException {

        Currency currency;
        try {
            currency = new Currency(name, iso);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(currency);

        return currency;
    }

    @Override
    public Customer createCustomer(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email) throws BillingRepositoryException {

        Customer customer;
        try {
            customer = new Customer(title, firstName, lastName, dateOfBirth, email);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }
        entityManager.persist(customer);

        return customer;
    }

    @Override
    public Discount createDiscount(int lowerAgeLimit, int upperAgeLimit, int discountPercentage) throws BillingRepositoryException {
        Discount discount;
        try {
            discount = new Discount(lowerAgeLimit, upperAgeLimit, discountPercentage);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(discount);

        return discount;
    }

    @Override
    public MedidataService createMedidataService(String name, Currency currency, BigDecimal cost) throws BillingRepositoryException {
        MedidataService medidataService;

        try {
            medidataService = new MedidataService(name, currency, cost);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(medidataService);

        return medidataService;
    }

    @Override
    public Insurance createInsurance(String name, String details,
                                     BigDecimal cost, MedidataService medidataService,
                                     int discountPercentage) throws BillingRepositoryException {
        Insurance insurance;

        try {
            insurance = new Insurance(name, details, cost, medidataService, discountPercentage);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(insurance);

        return insurance;
    }

    @Override
    public Invoice createInvoice(Customer customer, Currency currency) throws BillingRepositoryException {
        Invoice invoice;
        try {
            invoice = new Invoice(customer, currency, LocalDateTime.now());
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(invoice);

        return invoice;
    }

    @Override
    public MedidataServiceProduct createMedidataServiceProduct(String name, Currency currency,
                                                               BigDecimal cost, MedidataService medidataService)
            throws BillingRepositoryException {
        MedidataServiceProduct medidataServiceProduct;
        try {
            medidataServiceProduct = new MedidataServiceProduct(name, currency, cost, medidataService);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(medidataServiceProduct);

        return medidataServiceProduct;

    }

    @Override
    public InvoiceLine createInvoiceLine(Invoice invoice, MedidataService medidataService) throws BillingRepositoryException {

        InvoiceLine invoiceLine;
        try {
            invoiceLine = new InvoiceLine(invoice, medidataService);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(invoiceLine);

        BigDecimal invoiceLineTotal = invoiceLine.getTotal();
        BigDecimal newInvoiceTotal = invoice.getTotal().add(invoiceLineTotal);
        invoice.setTotal(newInvoiceTotal);
        entityManager.merge(invoice);

        return invoiceLine;
    }

    @Override
    public InvoiceLine createInvoiceLine(Invoice invoice, MedidataServiceProduct medidataServiceProduct) throws BillingRepositoryException {
        InvoiceLine invoiceLine;
        try {
            invoiceLine = new InvoiceLine(invoice, medidataServiceProduct);
        } catch (Exception e) {
            throw new BillingRepositoryException(e);
        }

        entityManager.persist(invoiceLine);

        BigDecimal invoiceLineTotal = invoiceLine.getTotal();
        BigDecimal newInvoiceTotal = invoice.getTotal().add(invoiceLineTotal);
        invoice.setTotal(newInvoiceTotal);
        entityManager.merge(invoice);

        return invoiceLine;
    }

    @Override
    public List<Customer> fetchAllCustomers() {
        //language=HQL
        String queryString = "from Customer ";

        List<Customer> resultList = entityManager.createQuery(queryString).getResultList();

        return resultList;
    }

    @Override
    public List<Customer> fetchAllCustomersWithInsurance() {
        //language=HQL
        String queryString = "select customer " +
                             "from Customer customer " +
                             "left join customer.insurance insurance " +
                             "where insurance is not null " +
                             "order by customer.firstName asc ";

        List<Customer> resultList = entityManager.createQuery(queryString).getResultList();

        return resultList;
    }

    @Override
    public List<Customer> fetchAllCustomersWithInsurance(String insuranceName) {
        //language=HQL
        String queryString = "select customer " +
                                "from Customer customer " +
                                "left join customer.insurance insurance " +
                                "where lower(insurance.name) = lower(:insuranceName) " +
                                "order by customer.firstName asc ";

        List<Customer> resultList = entityManager.createQuery(queryString)
                                                 .setParameter("insuranceName", insuranceName)
                                                 .getResultList();

        return resultList;
    }

    @Override
    public Customer findAndAddDiscount(Customer customer, int customerAge) {
        //language=HQL
        String queryString = "select discount " +
                                "from Discount discount " +
                                "where discount.lowerAgeLimit <= :customerAge " +
                                        " and discount.upperAgeLimit >= :customerAge ";

        Discount discount;

        try {
            discount = entityManager.createQuery(queryString, Discount.class)
                                    .setParameter("customerAge", customerAge)
                                    .getSingleResult();
        } catch (Exception e) {
            //its fine not to have a discount
            return customer;
        }

        customer.addDiscount(discount);
        customer = entityManager.merge(customer);


        return customer;
    }

    @Override
    public <T> T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <T> void remove(T entity) {
        entityManager.remove(entity);
    }


    @Override
    public boolean invoiceHasService(MedidataService medidataService) {
        //language=HQL
        String queryString = "select count(invoiceLine) " +
                                " from InvoiceLine invoiceLine " +
                                " where invoiceLine.medidataService = :medidataService";

        long count = entityManager.createQuery(queryString, Long.class)
                .setParameter("medidataService", medidataService)
                                    .getSingleResult();

        return count > 0;

    }

    @Override
    public boolean hasDependantServiceProduct(MedidataService medidataService) {

        //language=HQL
        String queryString = "select count(invoiceLine) " +
                                " from InvoiceLine invoiceLine " +
                                " left join invoiceLine.medidataServiceProduct medidataProduct " +
                                " where medidataProduct.medidataService = :medidataService ";

        long count = entityManager.createQuery(queryString, Long.class)
                                    .setParameter("medidataService", medidataService)
                                    .getSingleResult();

        return count > 0;
    }


}
