package com.itv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by sakibchoudhury on 22/12/17.
 */
@Entity
@Table(name = "till")
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Till {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(name = "store_no", nullable = false)
    private long storeNo;

    @Column(name = "till_number", nullable = false)
    private int tillNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;


    public Till(long storeNo, int tillNumber, Currency currency) {

        if(storeNo <= 0)
            throw new IllegalArgumentException("Store No can not be " + storeNo);

        if(tillNumber <=0 )
            throw new IllegalArgumentException("Till Number can not be " + tillNumber);

        if(currency == null)
            throw new IllegalArgumentException("Currency is null");

        this.storeNo = storeNo;
        this.tillNumber = tillNumber;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Till till = (Till) o;

        if (id != till.id) return false;
        if (version != till.version) return false;
        if (storeNo != till.storeNo) return false;
        return tillNumber == till.tillNumber;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (int) (storeNo ^ (storeNo >>> 32));
        result = 31 * result + tillNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Till{" +
                "id=" + id +
                ", version=" + version +
                ", storeNo=" + storeNo +
                ", tillNumber=" + tillNumber +
                '}';
    }
}

