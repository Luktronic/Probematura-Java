package at.spengergasse.ft2021pos1.part2.service;

import at.spengergasse.ft2021pos1.part2.domain.Gender;
import at.spengergasse.ft2021pos1.part2.domain.Test;
import at.spengergasse.ft2021pos1.part2.persistence.TestRepository;
import at.spengergasse.ft2021pos1.part2.service.statistic.AgeGroup;
import at.spengergasse.ft2021pos1.part2.service.statistic.AgeGroupValue;
import at.spengergasse.ft2021pos1.part2.service.statistic.Statistic;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class TestServiceTest {

    @Autowired
    private TestService testService;

    List<Test> tests = TestRepository.generateFakeTests();

    Statistic statistic;
    AgeGroupValue under10;
    AgeGroupValue under20;
    AgeGroupValue under30;
    AgeGroupValue under40;
    AgeGroupValue under50;
    AgeGroupValue under60;
    AgeGroupValue over60;

    @BeforeEach
    void setup() {
        statistic = testService.createGenderStatistic();

        under10 = statistic.ageGroupValues().get(AgeGroup.UNDER10);
        under20 = statistic.ageGroupValues().get(AgeGroup.UNDER20);
        under30 = statistic.ageGroupValues().get(AgeGroup.UNDER30);
        under40 = statistic.ageGroupValues().get(AgeGroup.UNDER40);
        under50 = statistic.ageGroupValues().get(AgeGroup.UNDER50);
        under60 = statistic.ageGroupValues().get(AgeGroup.UNDER60);
        over60 = statistic.ageGroupValues().get(AgeGroup.OVER60);
    }

    @org.junit.jupiter.api.Test
    void ensureGenerateStatisticWorks() {
        log.debug("Starting ensureGenerateStatisticWorks");

        //Assert
        log.debug("Checking if all age groups are included in the statistic");
        assertThat(statistic.ageGroupValues().size()).isEqualTo(AgeGroup.values().length);

        log.debug("Checking if all age groups contain all genders");
        val ageGroupValues = statistic.ageGroupValues();
        ageGroupValues.keySet().forEach(group -> {
            assertThat(ageGroupValues.get(group).genderValues().size()).isEqualTo(Gender.values().length);
        });

        log.info("Test ensureGenerateStatisticWorks passed!");
    }

    @org.junit.jupiter.api.Test
    void ensureStatisticDataIsCorrect() {
        log.debug("Starting ensureStatisticDataIsCorrect");
        long expectedInGroupUnder10 = tests.stream()
                .map(Test::getPerson)
                .filter(p -> AgeGroup.calculateAgeGroup(p.getBirthDate()).equals(AgeGroup.UNDER10))
                .count();

        //Assert
        log.debug("Checking if group under 10 has expected amount of people in it");
        assertThat(under10.total()).isEqualTo(expectedInGroupUnder10);

        log.info("Test ensureStatisticDataIsCorrect passed!");
    }

}
