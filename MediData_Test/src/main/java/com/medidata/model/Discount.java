package com.medidata.model;

import lombok.Getter;

import javax.persistence.*;

/**
 * This Discount is age based
 * Created by sakibchoudhury on 02/12/17.
 */
@Entity
@Table(name = "discount", uniqueConstraints = {@UniqueConstraint(columnNames = {"lower_age_limit","upper_age_limit"})})
@Getter
public class Discount {

    public static final int AGE_HIGHEST = 150;
    public static final int AGE_LOWEST = 0;

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(name = "lower_age_limit", nullable = false, length = 100)
    private int lowerAgeLimit;

    @Column(name = "upper_age_limit", nullable = false, length = 100)
    private int upperAgeLimit;

    @Column(name = "discount_percentage", nullable = false, length = 100)
    private double discountPercentage;

    public Discount(int lowerAgeLimit, int upperAgeLimit, double discountPercentage) {
        if(lowerAgeLimit < 0)
            throw new IllegalArgumentException("Lower age limit is less than 0");

        if(upperAgeLimit < 0)
            throw new IllegalArgumentException("Upper age limit is less than 0");

        if(discountPercentage <= 0 || discountPercentage > 99)
            throw new IllegalArgumentException("Discount Percentage Is Wrong!");

        if(lowerAgeLimit == 0 && upperAgeLimit == 0)
            throw new IllegalArgumentException("Both age limits cant be zero!");

        if(lowerAgeLimit > upperAgeLimit)
            throw new IllegalArgumentException("Upper age limit can not be more than lower age limit!");


        this.lowerAgeLimit = lowerAgeLimit;
        this.upperAgeLimit = upperAgeLimit;
        this.discountPercentage = discountPercentage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (id != discount.id) return false;
        if (version != discount.version) return false;
        if (lowerAgeLimit != discount.lowerAgeLimit) return false;
        if (upperAgeLimit != discount.upperAgeLimit) return false;
        return Double.compare(discount.discountPercentage, discountPercentage) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + lowerAgeLimit;
        result = 31 * result + upperAgeLimit;
        temp = Double.doubleToLongBits(discountPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", version=" + version +
                ", lowerAgeLimit=" + lowerAgeLimit +
                ", upperAgeLimit=" + upperAgeLimit +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
