package com.itv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 20/12/17.
 */
@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(nullable = false, unique = true)
    private String sku;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal price;

    @Embedded
    private SpecialPrice specialPrice;


    public Product(String sku, Currency currency, BigDecimal price) {
        if(sku == null || sku.isEmpty())
            throw new IllegalArgumentException("SKU is empty!");

        if(currency == null)
            throw new IllegalArgumentException("Currency is empty!");

        if(price == null || price.doubleValue() == 0)
            throw new IllegalArgumentException("Price is empty or Zero!");

        this.sku = sku;
        this.currency = currency;
        this.price = price;
    }


    @Transient
    public void addSpecialPrice(int units, double totalSpecialPrice) {

        if(units <= 1)
            throw new IllegalArgumentException("Unit is Unacceptable! Units should be 2 or more.");

        if(totalSpecialPrice <= 0)
            throw new IllegalArgumentException("Special Price is empty Or Unaccaptable!");

        BigDecimal savings = calculateSavings(units, new BigDecimal(totalSpecialPrice));

        if(savings.doubleValue() <= 0)
            throw new IllegalArgumentException("Savings is empty or Zero or Unaccaptable!");

        this.specialPrice = new SpecialPrice(units, new BigDecimal(totalSpecialPrice), savings);
    }

    private BigDecimal calculateSavings(int units, BigDecimal totalSpecialPrice) {
        BigDecimal totalPriceWithoutSavings = price.multiply(new BigDecimal(units));
        BigDecimal savings = totalPriceWithoutSavings.subtract(totalSpecialPrice);

        return savings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (version != product.version) return false;
        if (sku != null ? !sku.equals(product.sku) : product.sku != null) return false;
        if (currency != null ? !currency.equals(product.currency) : product.currency != null) return false;
        return !(price != null ? !price.equals(product.price) : product.price != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", version=" + version +
                ", sku='" + sku + '\'' +
                ", currency=" + currency +
                ", price=" + price +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
