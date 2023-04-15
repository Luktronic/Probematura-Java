package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "teststations")
public class TestStation extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @NotBlank
    private String stationName;

    public TestStation(String stationName) {
        this.stationName = stationName;
    }
}
