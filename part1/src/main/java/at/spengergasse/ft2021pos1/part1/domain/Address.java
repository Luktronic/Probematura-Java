package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

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
}
