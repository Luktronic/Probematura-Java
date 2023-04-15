package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "persons")
public class Person extends AbstractPersistable<Long> {

    @Version //  :)
    private Integer version;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Positive
    @Column(name = "ssn")
    private Integer socialSecurityNumber;
    @Column(name = "email")
    private String eMail;
    @Embedded
    private PhoneNumber phoneNumber;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "address_id")
    private Address address;


    /*
    Backreferences for bidirectional relations
     */

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Test> tests;


    //Constructor without ID and version
    public Person(String firstName, String lastName, Gender gender, LocalDate birthDate, Integer socialSecurityNumber, String eMail, PhoneNumber phoneNumber, Address address, Set<Test> tests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (!Objects.equals(firstName, person.firstName)) return false;
        if (!Objects.equals(lastName, person.lastName)) return false;
        if (gender != person.gender) return false;
        if (!Objects.equals(birthDate, person.birthDate)) return false;
        if (!Objects.equals(socialSecurityNumber, person.socialSecurityNumber))
            return false;
        if (!Objects.equals(eMail, person.eMail)) return false;
        if (!Objects.equals(phoneNumber, person.phoneNumber)) return false;
        if (!Objects.equals(address, person.address)) return false;
        return Objects.equals(tests, person.tests);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (socialSecurityNumber != null ? socialSecurityNumber.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (tests != null ? tests.hashCode() : 0);
        return result;
    }
}
