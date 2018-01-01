package com.itv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Created by sakibchoudhury on 21/12/17.
 */
@Entity
@Table(name = "till_user")
@Getter
@NoArgsConstructor
public class TillUser {

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

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;


    public TillUser(Title title,
                    String firstName,
                    String lastName,
                    LocalDate dateOfBirth,
                    String email,
                    String username,
                    String hashedPassword) {

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

        if(username == null || username.isEmpty())
            throw new IllegalArgumentException("Username is empty");

        if(hashedPassword == null || hashedPassword.isEmpty())
            throw new IllegalArgumentException("Password is empty");

        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TillUser tillUser = (TillUser) o;

        if (id != tillUser.id) return false;
        if (version != tillUser.version) return false;
        if (title != tillUser.title) return false;
        if (firstName != null ? !firstName.equals(tillUser.firstName) : tillUser.firstName != null) return false;
        if (lastName != null ? !lastName.equals(tillUser.lastName) : tillUser.lastName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(tillUser.dateOfBirth) : tillUser.dateOfBirth != null)
            return false;
        if (email != null ? !email.equals(tillUser.email) : tillUser.email != null) return false;
        if (username != null ? !username.equals(tillUser.username) : tillUser.username != null) return false;
        return !(hashedPassword != null ? !hashedPassword.equals(tillUser.hashedPassword) : tillUser.hashedPassword != null);

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
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (hashedPassword != null ? hashedPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TillUser{" +
                "id=" + id +
                ", version=" + version +
                ", title=" + title +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
