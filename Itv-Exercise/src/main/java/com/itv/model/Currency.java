package com.itv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by sakibchoudhury on 02/12/17.
 */

@Entity
@Table(name = "currency", uniqueConstraints = {@UniqueConstraint(columnNames = {"name","iso"})})
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Currency {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String iso;



    public Currency(String name, String iso) {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name is empty!");

        if(iso == null || iso.isEmpty())
            throw new IllegalArgumentException("Iso is empty");

        this.name = name;
        this.iso = iso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (id != currency.id) return false;
        if (version != currency.version) return false;
        if (name != null ? !name.equals(currency.name) : currency.name != null) return false;
        return !(iso != null ? !iso.equals(currency.iso) : currency.iso != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (iso != null ? iso.hashCode() : 0);
        result = 31 * result + (int) (version ^ (version >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iso='" + iso + '\'' +
                ", version=" + version +
                '}';
    }
}
