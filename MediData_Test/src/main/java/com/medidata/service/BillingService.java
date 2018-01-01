package com.medidata.service;

import com.medidata.model.*;
import com.medidata.service.impl.BillingServiceException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by sakibchoudhury on 04/12/17.
 */

public interface BillingService {
    Currency createCurrency(String name, String iso) throws BillingServiceException;

    Customer createCustomer(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email) throws BillingServiceException;

    Discount createDiscount(int lowerAgeLimit, int upperAgeLimit, int discountPercentage) throws BillingServiceException;

    MedidataService createService(String name, Currency currency, double cost) throws BillingServiceException;

    Insurance createInsurance(String name, String details, double cost, MedidataService medidataService, int discountPercentage) throws BillingServiceException;

    Invoice createInvoice(Customer customer, Currency currency) throws BillingServiceException;

    MedidataServiceProduct createServiceProduct(String name, Currency currency, double cost, MedidataService medidataService) throws BillingServiceException;

    InvoiceLine createInvoiceLine(Invoice invoice, MedidataService medidataService) throws BillingServiceException;

    InvoiceLine createInvoiceLine(Invoice invoice, MedidataServiceProduct medidataServiceProduct) throws BillingServiceException;

    List<Customer> fetchAllCustomers();

    List<Customer> fetchAllCustomersWithInsurance();

    List<Customer> fetchAllCustomersWithInsurance(String insuranceName) throws BillingServiceException;

    <T> T update(T entity);

    void removeInvoiceLine(InvoiceLine invoiceLine) throws BillingServiceException;
}
