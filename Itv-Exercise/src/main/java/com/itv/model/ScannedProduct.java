package com.itv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Getter
@Entity
@Table(name = "scanned_product")
@NoArgsConstructor
public class ScannedProduct {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(name = "scan_date_time", nullable = false)
    private LocalDateTime scanDateTime;

    @Column(name = "product_sku", nullable = false)
    private String productSku;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal price;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_checkout_id", nullable = false)
    private Checkout checkout;



    public ScannedProduct(LocalDateTime scanDateTime, String productSku, Currency currency, BigDecimal price) {
        this.scanDateTime = scanDateTime;
        this.productSku = productSku;
        this.currency = currency;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScannedProduct that = (ScannedProduct) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (scanDateTime != null ? !scanDateTime.equals(that.scanDateTime) : that.scanDateTime != null) return false;
        if (productSku != null ? !productSku.equals(that.productSku) : that.productSku != null) return false;
        return !(price != null ? !price.equals(that.price) : that.price != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (scanDateTime != null ? scanDateTime.hashCode() : 0);
        result = 31 * result + (productSku != null ? productSku.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScannedProduct{" +
                "id=" + id +
                ", version=" + version +
                ", scanDateTime=" + scanDateTime +
                ", productSku='" + productSku + '\'' +
                ", price=" + price +
                '}';
    }
}
