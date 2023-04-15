package at.spengergasse.ft2021pos1.part2.service.statistic;

import java.util.HashMap;
import java.util.Map;

public record Statistic(Map<AgeGroup, AgeGroupValue> ageGroupValues) {

    public Statistic() {
        this(new HashMap<>(7));
    }

}
