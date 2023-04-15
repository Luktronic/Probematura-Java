package at.spengergasse.ft2021pos1.part1.persistence;

import at.spengergasse.ft2021pos1.part1.domain.Address;
import at.spengergasse.ft2021pos1.part1.domain.Gender;
import at.spengergasse.ft2021pos1.part1.domain.Person;
import at.spengergasse.ft2021pos1.part1.domain.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Person person;
    private final PhoneNumber phoneNumber = new PhoneNumber(200, 101, 512);
    private final Address address = new Address("100", "1214", "Vienna", "Austria", new HashSet<>(1));

    @BeforeEach
    void setup() {
        person = new Person("Luka", "Furundzija", Gender.MALE, LocalDate.now().minusYears(19),
                100, "cool@mail.com", phoneNumber, address, new HashSet<>(1));
    }

    @Test
    void ensureSaveAndFindByIdWorks() {
        log.debug("Starting ensureSaveAndFindByIdWorks");

        //Act
        log.debug("Saving person [{}]", person);
        val savedPerson = personRepository.save(person);

        //Assert
        log.debug("Checking if returned Person is the same");
        assertThat(savedPerson).isEqualTo(person);

        log.debug("Checking if Person can be queried after being saved");
        val foundPerson = personRepository.findById(Objects.requireNonNull(person.getId()));
        assertThat(foundPerson.isPresent()).isTrue();
        assertThat(foundPerson.get()).isEqualTo(person);

        log.info("Test ensureSavePersonWorks passed!");
    }

    @Test
    void ensureFindByPhoneNumberWorks() {
        log.debug("Starting ensureFindByPhoneNumberWorks");

        //Act
        log.debug("Saving person [{}]", person);
        personRepository.save(person);

        //Assert
        log.debug("Checking if Person can be queried by phone number [{}]", phoneNumber);
        val foundPerson = personRepository.findByPhoneNumber(phoneNumber);
        assertThat(foundPerson.isPresent()).isTrue();
        assertThat(foundPerson.get()).isEqualTo(person);

        log.info("Test ensureFindByPhoneNumberWorks passed!");
    }

    @Test
    void ensureFindByZipCodeWorks() {
        log.debug("Starting ensureFindByZipCodeWorks");

        //Act
        log.debug("Saving person [{}]", person);
        personRepository.save(person);

        //Assert
        log.debug("Checking if Person can be queried by zip code [{}]", address.getZipCode());
        val foundPerson = personRepository.findByAddress_ZipCode(address.getZipCode());
        assertThat(foundPerson.isPresent()).isTrue();
        assertThat(foundPerson.get()).isEqualTo(person);

        log.info("Test ensureFindByZipCodeWorks passed!");
    }

    @Test
    void ensureFindByCityWorks() {
        log.debug("Starting ensureFindByCityWorks");
        //Assign
        //Query is %ienn% -> city must contain 'ienn' in name
        String cityQuery = "%" + address.getCity().substring(1, 5) + "%";

        //Act
        log.debug("Saving person [{}]", person);
        personRepository.save(person);

        //Assert
        log.debug("Checking if Person can be queried by part of city name [{}]", address.getCity().substring(1, 5));
        val foundPerson = personRepository.findByAddress_CityLikeIgnoreCase(cityQuery);
        assertThat(foundPerson.isPresent()).isTrue();
        assertThat(foundPerson.get()).isEqualTo(person);

        log.info("Test ensureFindByCityWorks passed!");
    }
}
