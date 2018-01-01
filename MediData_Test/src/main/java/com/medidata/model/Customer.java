package com.medidata.model;

import lombok.Getter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
@Entity
@Table(name = "customer")
@Getter
public class Customer {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    @Version
    @Column(nullable = false)
    private long version;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Title title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Email(message="Please provide a valid email address")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @Column(unique = true, nullable = false)
    private String email;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_insurance_id")
    private Insurance insurance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_discount_id")
    private Discount discount;

    public Customer(Title title, String firstName, String lastName, LocalDate dateOfBirth, String email) {

        if(title == null)
            throw new IllegalArgumentException("Title is Null!");

        if(firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("First Name is empty!");

        if(lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("Last Name is empty!");

        if(dateOfBirth == null)
            throw new IllegalArgumentException("Date of birth is empty!");

        if(email == null || email.isEmpty())
            throw new IllegalArgumentException("Email is empty");

        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }


    @Transient
    public void addInsurance(Insurance insurance) {
        if(insurance == null)
            throw new IllegalArgumentException("Insurance is null!");

        if(insurance.getId() == 0)
            throw new IllegalArgumentException("Insurance is Detached Or Not Saved!");

        this.insurance = insurance;
    }

    /**
     * This should be automatically added when the customer is created
     * @param discount
     */
    @Transient
    public void addDiscount(Discount discount) {
        if(discount == null)
            throw new IllegalArgumentException("Discount is null!");

        if(discount.getId() == 0)
            throw new IllegalArgumentException("Discount is Detached Or Not Saved!");

        this.discount = discount;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (version != customer.version) return false;
        if (title != customer.title) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(customer.dateOfBirth) : customer.dateOfBirth != null)
            return false;
        return !(email != null ? !email.equals(customer.email) : customer.email != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", version=" + version +
                ", title=" + title +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", insurance=" + insurance +
                '}';
    }
}
