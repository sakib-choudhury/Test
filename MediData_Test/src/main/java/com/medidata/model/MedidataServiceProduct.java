package com.medidata.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
@Entity
@Table(name = "medidata_service_product")
@Getter
public class MedidataServiceProduct {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_medidata_service_id", nullable = false)
    private MedidataService medidataService;

    public MedidataServiceProduct(String name, Currency currency, BigDecimal cost, MedidataService medidataService) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name is Empty!");

        if(currency == null)
            throw new  IllegalArgumentException("Currency is Null!");

        if(cost == null)
            throw new  IllegalArgumentException("Cost is Null!");

        if(cost.floatValue() < 0)
            throw new IllegalArgumentException("Cost is less than 0!");

        if(medidataService == null)
            throw new  IllegalArgumentException("Service is Null!");

        if(medidataService.getId() == 0)
            throw new IllegalArgumentException("Service is Detached Or Not Saved!");

        this.name = name;
        this.currency = currency;
        this.cost = cost;
        this.medidataService = medidataService;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedidataServiceProduct that = (MedidataServiceProduct) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        return !(cost != null ? !cost.equals(that.cost) : that.cost != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceProduct{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", cost=" + cost +
                '}';
    }
}
