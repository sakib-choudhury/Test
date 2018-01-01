package com.medidata.model;

import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
@Entity
@Table(name = "medidata_service")
@Getter
public class MedidataService {

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
    @JoinColumn(name = "fk_currency_id")
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal cost;


    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medidataService")
    private List<MedidataServiceProduct> medidataServiceProductList;

    public MedidataService(String name, Currency currency, BigDecimal cost) {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name is empty!");

        if(currency == null)
            throw new IllegalArgumentException("Currency is Null");

        if(cost == null)
            throw new  IllegalArgumentException("Cost is Null!");

        if(cost.floatValue() < 0)
            throw new IllegalArgumentException("Cost is less than 0!");


        this.name = name;
        this.currency = currency;
        this.cost = cost;
    }

    @Transient
    public void addServiceProduct(MedidataServiceProduct medidataServiceProduct) {
        if(medidataServiceProduct == null)
            throw new IllegalArgumentException("Service Product is null!");

        if(medidataServiceProduct.getId() == 0)
            throw new IllegalArgumentException("Service Product is Detached Or Not Saved!");

        if(medidataServiceProductList == null)
            medidataServiceProductList = new ArrayList<>();

        medidataServiceProductList.add(medidataServiceProduct);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedidataService medidataService = (MedidataService) o;

        if (id != medidataService.id) return false;
        if (version != medidataService.version) return false;
        if (name != null ? !name.equals(medidataService.name) : medidataService.name != null) return false;
        if (currency != null ? !currency.equals(medidataService.currency) : medidataService.currency != null) return false;
        return !(cost != null ? !cost.equals(medidataService.cost) : medidataService.cost != null);

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
        return "Service{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", cost=" + cost +
                '}';
    }
}
