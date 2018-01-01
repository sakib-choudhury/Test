package com.medidata.model;

import com.medidata.util.MoneteryFormatUtil;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 03/12/17.
 */
@Entity
@Table(name = "invoice_line")
@Getter
public class InvoiceLine {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_invoice_id", nullable = false)
    private Invoice invoice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_medidata_service_id")
    private MedidataService medidataService;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_medidata_service_product_id")
    private MedidataServiceProduct medidataServiceProduct;

    //@Setter
    @Column(nullable = false)
    private BigDecimal total = new BigDecimal(0);

    public InvoiceLine(Invoice invoice, MedidataService medidataService) {

        if(invoice == null)
            throw new IllegalArgumentException("Invoice is Null");

        if(invoice.getId() == 0)
            throw new IllegalArgumentException("Invoice is detached");

        if(medidataService == null)
            throw new IllegalArgumentException("Service is Null");

        if(medidataService.getId() == 0)
            throw new IllegalArgumentException("Service is detached");

        this.invoice = invoice;
        this.medidataService = medidataService;
        this.total = calculateTotalForMedidataService(invoice, medidataService);
    }

    public InvoiceLine(Invoice invoice, MedidataServiceProduct medidataServiceProduct) {

        if(invoice == null)
            throw new IllegalArgumentException("Invoice is Null");

        if(invoice.getId() == 0)
            throw new IllegalArgumentException("Invoice is detached");

        if(medidataServiceProduct == null)
            throw new IllegalArgumentException("Service Product is Null");

        if(medidataServiceProduct.getId() == 0)
            throw new IllegalArgumentException("Service Product is detached");

        this.invoice = invoice;
        this.medidataServiceProduct = medidataServiceProduct;
        this.total = calculateTotalForMedidataServiceProduct(invoice, medidataServiceProduct);
    }


    @Transient
    private BigDecimal calculateTotalForMedidataService(Invoice invoice, MedidataService medidataService) {

        BigDecimal serviceCost = medidataService.getCost();
        Customer customer = invoice.getCustomer();

        double discountPercentage = customer.getDiscount() != null ? customer.getDiscount().getDiscountPercentage() : 0;

        discountPercentage += calculateDiscountPercentageForInsurance(customer, medidataService);

        if(discountPercentage > 0) {
            serviceCost = serviceCost.multiply(new BigDecimal(100 - discountPercentage)).divide(new BigDecimal(100));
        }

        return serviceCost;
    }


    private double calculateDiscountPercentageForInsurance(Customer customer, MedidataService medidataService) {

        if(customer.getInsurance() == null)
            return 0;

        if(!customer.getInsurance().getMedidataService().equals(medidataService))
            return 0;

        return customer.getInsurance().getDiscountPercentage();

    }


    @Transient
    private BigDecimal calculateTotalForMedidataServiceProduct(Invoice invoice, MedidataServiceProduct medidataServiceProduct) {

        BigDecimal serviceCost = medidataServiceProduct.getCost();
        Customer customer = invoice.getCustomer();

        double discountPercentage = customer.getDiscount() != null ? customer.getDiscount().getDiscountPercentage() : 0;

        if(discountPercentage > 0) {
            serviceCost = serviceCost.multiply(new BigDecimal(100 - discountPercentage)).divide(new BigDecimal(100));
        }

        return serviceCost;
    }


    @Transient
    public String calculateTotal(String pattern) {
        return MoneteryFormatUtil.productFormattedMoneyByPattern(total, pattern);
    }

    @Transient
    public String calculateTotalToTwoDecimal() {
        return calculateTotal(MoneteryFormatUtil.TWO_DECIMAL_PATTERN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLine that = (InvoiceLine) o;

        if (id != that.id) return false;
        return version == that.version;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "id=" + id +
                ", version=" + version +
                ", invoice=" + invoice +
                ", service=" + (medidataService != null ? medidataService.getName() : null)  +
                ", serviceProduct=" + (medidataServiceProduct != null ? medidataServiceProduct.getName() : null) +
                '}';
    }
}
