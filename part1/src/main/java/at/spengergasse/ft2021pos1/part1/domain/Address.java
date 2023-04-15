package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "addresses")
public class Address extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @NotBlank
    private String streetNumber;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;
    @NotBlank
    private String country;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Person> persons;

    public Address(String streetNumber, String zipCode, String city, String country, Set<Person> persons) {
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNumber='" + streetNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
