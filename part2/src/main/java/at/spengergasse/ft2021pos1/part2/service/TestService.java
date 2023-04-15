package at.spengergasse.ft2021pos1.part2.service;

import at.spengergasse.ft2021pos1.part2.domain.Gender;
import at.spengergasse.ft2021pos1.part2.domain.Test;
import at.spengergasse.ft2021pos1.part2.persistence.TestRepository;
import at.spengergasse.ft2021pos1.part2.service.statistic.AgeGroup;
import at.spengergasse.ft2021pos1.part2.service.statistic.AgeGroupValue;
import at.spengergasse.ft2021pos1.part2.service.statistic.GenderValue;
import at.spengergasse.ft2021pos1.part2.service.statistic.Statistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor

@Slf4j
@Service
public class TestService {

    private final TestRepository testRepository;

    public Statistic createGenderStatistic() {
        log.debug("Creating gender statistic");
        val statistic = new Statistic();

        log.debug("Fetching all tests");
        val tests = testRepository.getTests();

        log.debug("Going through all AgeGroups");
        Arrays.stream(AgeGroup.values()).forEach(ageGroup -> {
            log.debug("Getting tests in age group [{}]", ageGroup.getName());
            List<Test> testsInAgeGroup = tests.stream()
                    .filter(test -> AgeGroup.calculateAgeGroup(test.getPerson().getBirthDate()).equals(ageGroup))
                    .toList();

            log.debug("Creating new AgeGroupValue");
            val ageGroupValue = new AgeGroupValue();

            log.debug("Going through all Genders");
            Arrays.stream(Gender.values()).forEach( gender -> {
                log.debug("Getting absolute amount of tests for gender [{}]", gender);
                long testsInGender = testsInAgeGroup.stream()
                        .filter(test -> test.getPerson().getGender().equals(gender))
                        .count();

                log.debug("Calculating percent amount for gender [{}]", gender);
                Double percentage = ((double) testsInGender / (double) tests.size());

                log.debug("Adding data for gender [{}] to age group [{}]", gender, ageGroup);
                ageGroupValue.genderValues().put(gender, new GenderValue(testsInGender, percentage));
            });

            log.debug("Adding data for age group [{}] to statistic", ageGroup);
            statistic.ageGroupValues().put(ageGroup, ageGroupValue);
        });

        return statistic;
    }

}
