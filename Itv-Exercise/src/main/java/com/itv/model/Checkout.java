package com.itv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by sakibchoudhury on 21/12/17.
 */
@Getter
@Entity
@Table(name = "checkout")
@NoArgsConstructor
public class Checkout {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_till_id", nullable = false)
    private Till till;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_till_user_id", nullable = false)
    private TillUser tillUser;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checkout")
    private Set<ScannedProduct> scannedProductSet = new LinkedHashSet<>();

    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checkout")
    private Set<ScannedProductDiscount> scannedProductDiscountSet = new LinkedHashSet<>();


    public Checkout(Till till, TillUser tillUser,
                    BigDecimal subTotal, BigDecimal total,
                    LocalDateTime checkoutStartTime, LocalDateTime checkoutEndTime) {

        if(till == null)
            throw new IllegalArgumentException("Till is null!");

        if(tillUser == null)
            throw new IllegalArgumentException("Till User is null!");

        if(subTotal == null)
            throw new IllegalArgumentException("Sub Total is null!");

        if(total == null)
            throw new IllegalArgumentException("Total is null!");

        if(checkoutStartTime == null)
            throw new IllegalArgumentException("Checkout start time is null!");

        if(checkoutEndTime == null)
            throw new IllegalArgumentException("Checkout end time is null!");

        this.till = till;
        this.tillUser = tillUser;
        this.subTotal = subTotal;
        this.total = total;
        startTime = checkoutStartTime;
        endTime = checkoutEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkout checkout = (Checkout) o;

        if (id != checkout.id) return false;
        if (version != checkout.version) return false;
        if (subTotal != null ? !subTotal.equals(checkout.subTotal) : checkout.subTotal != null) return false;
        if (total != null ? !total.equals(checkout.total) : checkout.total != null) return false;
        if (startTime != null ? !startTime.equals(checkout.startTime) : checkout.startTime != null) return false;
        return !(endTime != null ? !endTime.equals(checkout.endTime) : checkout.endTime != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (subTotal != null ? subTotal.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                ", version=" + version +
                ", subTotal=" + subTotal +
                ", total=" + total +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
