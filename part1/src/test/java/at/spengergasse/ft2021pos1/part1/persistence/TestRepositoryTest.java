package at.spengergasse.ft2021pos1.part1.persistence;

import at.spengergasse.ft2021pos1.part1.domain.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jboss.jandex.Result;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    private Test test;
    private final PhoneNumber phoneNumber = new PhoneNumber(200, 101, 512);
    private final Address address = new Address("100", "1214",
            "Vienna", "Austria", new HashSet<>(1));

    private final Person person = new Person("Luka", "Furundzija", Gender.MALE,
            LocalDate.now().minusYears(19), 100, "cool@mail.com",
            phoneNumber, address, new HashSet<>(1));
    private final TestStation testStation = new TestStation("VIC");

    @BeforeEach
    void setup() {
        test = new Test(LocalDateTime.now().minusDays(1), person, testStation, 10, TestKitType.PCR, TestResult.Negative);
    }

    @org.junit.jupiter.api.Test
    void ensureSaveAndFindByIdWorks() {
        log.debug("Starting ensureSaveAndFindByIdWorks");

        //Act
        log.debug("Saving Test [{}]", test);
        val savedTest = testRepository.save(test);

        //Assert
        log.debug("Checking if returned Test is the same");
        assertThat(savedTest).isEqualTo(test);

        log.debug("Checking if Person can be queried after being saved");
        val foundTest = testRepository.findById(Objects.requireNonNull(test.getId()));
        assertThat(foundTest.isPresent()).isTrue();
        assertThat(foundTest.get()).isEqualTo(test);

        log.info("Test ensureSaveAndFindByIdWorks passed!");
    }

    @org.junit.jupiter.api.Test
    void ensureCountByKitAndResultAndTestTimeWorks() {
        log.debug("Starting ensureCountByKitAndResultAndTestTimeWorks");
        //Assign
        val from = LocalDateTime.now().minusDays(20);
        val to = LocalDateTime.now();
        val testKitType = TestKitType.PCR;
        val result = TestResult.Negative;

        //Act
        log.debug("Saving Test [{}]", test);
        val savedTest = testRepository.save(test);

        log.debug("Executing count query");
        val count = testRepository.countDistinctByTestKitTypeAndResultAndTestTimeStampBetween(testKitType, result, from, to);

        //Assert
        log.debug("Checking if counting works");
        assertThat(count).isEqualTo(1);

        log.info("Test ensureCountByKitAndResultAndTestTimeWorks passed!");
    }

}
