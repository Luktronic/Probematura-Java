package at.spengergasse.ft2021pos1.part2.service.statistic;

import at.spengergasse.ft2021pos1.part2.domain.Test;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Function;

@Slf4j
public enum AgeGroup {


    UNDER10("<10"),
    UNDER20("<20"),
    UNDER30("<30"),
    UNDER40("<40"),
    UNDER50("<50"),
    UNDER60("<60"),
    OVER60(">60");
    private final String name;

    AgeGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AgeGroup calculateAgeGroup(LocalDate birthDate) {
        val yearDifference = birthDate.until(LocalDate.now()).getYears();
        log.debug("Difference between now [{}] and birthdate [{}] -> [{}] years", LocalDate.now(), birthDate, yearDifference);

        if(yearDifference < 10)
            return UNDER10;
        else if (yearDifference > 10 && yearDifference < 20)
            return UNDER20;
        else if (yearDifference > 20 && yearDifference < 30)
            return UNDER30;
        else if (yearDifference > 30 && yearDifference < 40)
            return UNDER40;
        else if (yearDifference > 40 && yearDifference < 50)
            return UNDER50;
        else if (yearDifference > 50 && yearDifference < 60)
            return UNDER60;
        else
            return OVER60;
    }
}
