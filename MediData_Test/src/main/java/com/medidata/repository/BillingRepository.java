package com.medidata.repository;

import com.medidata.model.*;
import com.medidata.repository.impl.BillingRepositoryException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by sakibchoudhury on 04/12/17.
 */
public interface BillingRepository {
    Currency createCurrency(String name, String iso) throws BillingRepositoryException;

    Customer createCustomer(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email) throws BillingRepositoryException;

    Discount createDiscount(int lowerAgeLimit, int upperAgeLimit, int discountPercentage) throws BillingRepositoryException;

    MedidataService createMedidataService(String name, Currency currency, BigDecimal cost) throws BillingRepositoryException;

    Insurance createInsurance(String name, String details, BigDecimal cost, MedidataService medidataService, int discountPercentage) throws BillingRepositoryException;

    Invoice createInvoice(Customer customer, Currency currency) throws BillingRepositoryException;

    MedidataServiceProduct createMedidataServiceProduct(String name, Currency currency, BigDecimal cost, MedidataService medidataService) throws BillingRepositoryException;

    InvoiceLine createInvoiceLine(Invoice invoice, MedidataService medidataService) throws BillingRepositoryException;

    InvoiceLine createInvoiceLine(Invoice invoice, MedidataServiceProduct medidataServiceProduct) throws BillingRepositoryException;

    List<Customer> fetchAllCustomers();

    List<Customer> fetchAllCustomersWithInsurance();

    List<Customer> fetchAllCustomersWithInsurance(String insuranceName);

    Customer findAndAddDiscount(Customer customer, int customerAge);

    <T> T update(T entity);

    <T> void remove(T entity);

    boolean invoiceHasService(MedidataService medidataService);


    boolean hasDependantServiceProduct(MedidataService medidataService);
}
