package com.medidata.repository;

import com.medidata.model.MedidataService;
import com.medidata.model.MedidataServiceProduct;
import com.medidata.repository.impl.BillingRepositoryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sakibchoudhury on 04/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@org.springframework.transaction.annotation.Transactional
public class BillingRepositoryTest {

    @Autowired
    private BillingRepository billingRepository;

    @Test(expected = BillingRepositoryException.class)
    public void testCreateCustomerFail() throws BillingRepositoryException {
        billingRepository.createCustomer(null, null, null, null, null);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateDiscountFail() throws BillingRepositoryException {
        billingRepository.createDiscount(0, 0, 0);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateMedidataServiceFail() throws BillingRepositoryException {
        billingRepository.createMedidataService(null, null, null);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateInsuranceFail() throws BillingRepositoryException {
        billingRepository.createInsurance(null, null, null, null, 0);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateInvoiceFail() throws BillingRepositoryException {
        billingRepository.createInvoice(null, null);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateMedidataServiceProductFail() throws BillingRepositoryException {
        billingRepository.createMedidataServiceProduct(null, null, null, null);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateInvoiceLine1Fail() throws BillingRepositoryException {
        billingRepository.createInvoiceLine(null, (MedidataService)null);

        Assert.fail();
    }

    @Test(expected = BillingRepositoryException.class)
    public void testCreateInvoiceLine2Fail() throws BillingRepositoryException {
        billingRepository.createInvoiceLine(null, (MedidataServiceProduct)null);

        Assert.fail();
    }
}
