package com.medidata.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 02/12/17.
 */

@Entity
@Table(name = "insurance")
@Getter
public class Insurance {



    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition="TEXT", nullable = false)
    private String details;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id")
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal cost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_medidata_service_id", nullable = false)
    private MedidataService medidataService;

    @Column(name = "discount_percentage", nullable = false)
    private double discountPercentage;

    public Insurance(String name, String details, BigDecimal cost, MedidataService medidataService, int discountPercentage) {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name is empty!");

        if(details == null || details.isEmpty())
            throw new IllegalArgumentException("Details is empty!");

        if(cost == null)
            throw new IllegalArgumentException("Cost is empty!");

        if(cost.floatValue() < 0)
            throw new IllegalArgumentException("Cost is less than 0!");

        if(medidataService == null)
            throw new IllegalArgumentException("Service is empty!");

        if(discountPercentage <= 0 || discountPercentage > 99)
            throw new IllegalArgumentException("Discount Percentage Is Wrong!");

        this.name = name;
        this.details = details;
        this.currency = medidataService.getCurrency();
        this.cost = cost;
        this.medidataService = medidataService;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Insurance insurance = (Insurance) o;

        if (id != insurance.id) return false;
        if (version != insurance.version) return false;
        if (Double.compare(insurance.discountPercentage, discountPercentage) != 0) return false;
        if (name != null ? !name.equals(insurance.name) : insurance.name != null) return false;
        if (details != null ? !details.equals(insurance.details) : insurance.details != null) return false;
        return !(cost != null ? !cost.equals(insurance.cost) : insurance.cost != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        temp = Double.doubleToLongBits(discountPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", cost=" + cost +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
