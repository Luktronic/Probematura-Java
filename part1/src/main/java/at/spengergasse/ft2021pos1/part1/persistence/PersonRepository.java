package at.spengergasse.ft2021pos1.part1.persistence;

import at.spengergasse.ft2021pos1.part1.domain.Person;
import at.spengergasse.ft2021pos1.part1.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByPhoneNumber(PhoneNumber phoneNumber);

    Optional<Person> findByAddress_ZipCode(String zipCode);

    Optional<Person> findByAddress_CityLikeIgnoreCase(String city);




}
