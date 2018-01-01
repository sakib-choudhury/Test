package com.medidata.model;

import com.medidata.util.MoneteryFormatUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
@Entity
@Table(name = "invoice")
@Getter
public class Invoice {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_customer_id", nullable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Setter
    @Column(nullable = false)
    private BigDecimal total = new BigDecimal(0);

    public Invoice(Customer customer, Currency currency, LocalDateTime dateTime) {

        if(customer == null)
            throw new IllegalArgumentException("Customer is Null");

        if(customer.getId() == 0)
            throw new IllegalArgumentException("Customer is Detached");

        if(currency == null)
            throw new IllegalArgumentException("Currency is Null");

        if(currency.getId() == 0)
            throw new IllegalArgumentException("Currency is Detached");

        if(dateTime == null)
            throw new IllegalArgumentException("DateTime is null");

        this.customer = customer;
        this.currency = currency;
        this.dateTime = dateTime;
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

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (version != invoice.version) return false;
        if (currency != null ? !currency.equals(invoice.currency) : invoice.currency != null) return false;
        return !(dateTime != null ? !dateTime.equals(invoice.dateTime) : invoice.dateTime != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", version=" + version +
                ", customer=" + customer +
                ", currency=" + currency +
                ", dateTime=" + dateTime +
                '}';
    }
}
