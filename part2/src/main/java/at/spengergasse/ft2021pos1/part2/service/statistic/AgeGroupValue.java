package at.spengergasse.ft2021pos1.part2.service.statistic;

import at.spengergasse.ft2021pos1.part2.domain.Gender;

import java.util.HashMap;
import java.util.Map;

public record AgeGroupValue(Map<Gender, GenderValue> genderValues) {

    public AgeGroupValue() {
        this(new HashMap<>(3));
    }

    /**
     * Gets the total amount of people in this group
     * @return total amount of people in this group
     */
    public long total() {
       return genderValues.keySet().stream()
                .map(genderValues::get)
                .map(GenderValue::absolute)
                .mapToLong(Long::longValue)
                .sum();
    }
}
