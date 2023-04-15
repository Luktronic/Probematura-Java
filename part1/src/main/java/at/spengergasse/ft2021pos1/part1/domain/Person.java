package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.Set;

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

    @OneToMany
    private Set<Test> tests;

}
