package com.medidata.service.impl;

import com.medidata.model.*;
import com.medidata.repository.BillingRepository;
import com.medidata.repository.impl.BillingRepositoryException;
import com.medidata.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Created by sakibchoudhury on 04/12/17.
 */
@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public Currency createCurrency(String name, String iso) throws BillingServiceException {
        Currency currency;
        try {
            currency = billingRepository.createCurrency(name, iso);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }
        return currency;
    }

    @Override
    public Customer createCustomer(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email) throws BillingServiceException {

        Customer customer;

        try {
            customer = billingRepository.createCustomer(title, firstName, lastName, dateOfBirth, email);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        int customerAge = calculateAge(dateOfBirth);

        if(customerAge > Discount.AGE_HIGHEST)
            throw new BillingServiceException("Customer Age is more than " + Discount.AGE_HIGHEST + " years!");

        customer = billingRepository.findAndAddDiscount(customer, customerAge);

        return customer;
    }

    private int calculateAge(LocalDate birthDate) {
            return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public Discount createDiscount(int lowerAgeLimit, int upperAgeLimit, int discountPercentage) throws BillingServiceException {
        Discount discount;

        try {
            discount = billingRepository.createDiscount(lowerAgeLimit, upperAgeLimit, discountPercentage);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return discount;
    }

    @Override
    public MedidataService createService(String name, Currency currency, double cost) throws BillingServiceException {
        MedidataService medidataService;

        try {
            medidataService = billingRepository.createMedidataService(name, currency, new BigDecimal(cost));
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return medidataService;
    }

    @Override
    public Insurance createInsurance(String name, String details, double cost,
                                     MedidataService medidataService, int discountPercentage) throws BillingServiceException {
        Insurance insurance;

        try {
            insurance = billingRepository.createInsurance(name, details, new BigDecimal(cost), medidataService, discountPercentage);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return insurance;
    }

    @Override
    public Invoice createInvoice(Customer customer, Currency currency) throws BillingServiceException {
        Invoice invoice;

        try {
            invoice = billingRepository.createInvoice(customer, currency);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return invoice;
    }

    @Override
    public MedidataServiceProduct createServiceProduct(String name, Currency currency,
                                                       double cost, MedidataService medidataService)
            throws BillingServiceException {
        MedidataServiceProduct medidataServiceProduct;

        try {
            medidataServiceProduct = billingRepository.createMedidataServiceProduct(name, currency, new BigDecimal(cost), medidataService);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return medidataServiceProduct;
    }

    @Override
    public InvoiceLine createInvoiceLine(Invoice invoice, MedidataService medidataService) throws BillingServiceException {

        if(invoice == null) {
            throw new BillingServiceException("Invoice is null!");
        }

        if(medidataService == null) {
            throw new BillingServiceException("MedidataService is null!");
        }

        if(!invoice.getCurrency().equals(medidataService.getCurrency())) {
            throw new BillingServiceException("Invoice and Medidata Service have different Currencies!");
        }

        InvoiceLine invoiceLine;
        try {
            invoiceLine = billingRepository.createInvoiceLine(invoice, medidataService);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return invoiceLine;
    }

    @Override
    public InvoiceLine createInvoiceLine(Invoice invoice, MedidataServiceProduct medidataServiceProduct) throws BillingServiceException {

        if(invoice == null) {
            throw new BillingServiceException("Invoice is null!");
        }

        if(medidataServiceProduct == null) {
            throw new BillingServiceException("MedidataServiceProduct is null!");
        }

        if(!invoice.getCurrency().equals(medidataServiceProduct.getCurrency())) {
            throw new BillingServiceException("Invoice and Medidata Service Product have different Currencies!");
        }


        boolean invoiceHasService = billingRepository.invoiceHasService(medidataServiceProduct.getMedidataService());

        if(!invoiceHasService)
            throw new BillingServiceException("Medidata Service: " + medidataServiceProduct.getMedidataService().getName() + " not Added before adding its corresponding Product!");


        InvoiceLine invoiceLine;
        try {
            invoiceLine = billingRepository.createInvoiceLine(invoice, medidataServiceProduct);
        } catch (BillingRepositoryException e) {
            throw new BillingServiceException(e);
        }

        return invoiceLine;
    }

    @Override
    public List<Customer> fetchAllCustomers() {
        return billingRepository.fetchAllCustomers();
    }

    @Override
    public List<Customer> fetchAllCustomersWithInsurance() {
        return billingRepository.fetchAllCustomersWithInsurance();
    }

    @Override
    public List<Customer> fetchAllCustomersWithInsurance(String insuranceName) throws BillingServiceException {
        if(insuranceName == null || insuranceName.isEmpty())
            throw new BillingServiceException("Insurance name is null!");

        return billingRepository.fetchAllCustomersWithInsurance(insuranceName);
    }

    @Override
    public <T> T update(T entity) {
        return billingRepository.update(entity);
    }

    @Override
    public void removeInvoiceLine(InvoiceLine invoiceLine) throws BillingServiceException {
        if(invoiceLine == null)
            throw new BillingServiceException("Invoice is null!");

        if(invoiceLine.getMedidataService() != null) {
            boolean hasDependantServiceProduct = billingRepository.hasDependantServiceProduct(invoiceLine.getMedidataService());

            if(hasDependantServiceProduct)
                throw new BillingServiceException("Can not remove Service as it has dependant Service Product!");
        }

        BigDecimal invoiceLineTotal = invoiceLine.getTotal();

        Invoice invoice = invoiceLine.getInvoice();

        BigDecimal newInvoiceTotal = invoice.getTotal().subtract(invoiceLineTotal);

        invoice.setTotal(newInvoiceTotal);

        billingRepository.remove(invoiceLine);
        billingRepository.update(invoice);
    }
}
