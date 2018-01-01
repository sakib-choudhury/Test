package com.itv.model;

import com.itv.util.MonetaryFormatUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sakibchoudhury on 20/12/17.
 */
@Getter
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor
public class SpecialPrice {

    @Column(name = "units")
    private int units;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "savings")
    private BigDecimal savings;

    public SpecialPrice(int units, BigDecimal totalPrice, BigDecimal savings) {
        this.units = units;
        this.totalPrice = totalPrice;
        this.savings = savings;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialPrice that = (SpecialPrice) o;

        if (units != that.units) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        return !(savings != null ? !savings.equals(that.savings) : that.savings != null);

    }

    @Override
    public int hashCode() {
        int result = units;
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (savings != null ? savings.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpecialPrice{" +
                "units=" + units +
                ", totalPrice=" + totalPrice +
                ", savings=" + savings +
                '}';
    }
}
