package com.itv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Getter
@Entity
@Table(name = "scanned_product_discount")
@NoArgsConstructor
public class ScannedProductDiscount {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(nullable = false, unique = true)
    private String productSku;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal savings;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_checkout_id", nullable = false)
    private Checkout checkout;

    public ScannedProductDiscount(String productSku, Currency currency, BigDecimal savings) {
        this.productSku = productSku;
        this.currency = currency;
        this.savings = savings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScannedProductDiscount that = (ScannedProductDiscount) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (productSku != null ? !productSku.equals(that.productSku) : that.productSku != null) return false;
        return !(savings != null ? !savings.equals(that.savings) : that.savings != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (productSku != null ? productSku.hashCode() : 0);
        result = 31 * result + (savings != null ? savings.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScannedProductDiscount{" +
                "id=" + id +
                ", version=" + version +
                ", productSku='" + productSku + '\'' +
                ", savings=" + savings +
                '}';
    }
}
