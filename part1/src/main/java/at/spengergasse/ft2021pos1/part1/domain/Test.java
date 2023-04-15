package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tests")
public class Test extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @NotNull
    @PastOrPresent
    private LocalDateTime testTimeStamp;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Person person;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private TestStation station;

    private Integer testBay;
    @Enumerated(EnumType.STRING)
    private TestKitType testKitType;
    @Enumerated(EnumType.STRING)
    @Column(name = "test_result")
    private TestResult result;

    public Test(LocalDateTime testTimeStamp, Person person, TestStation station, Integer testBay, TestKitType testKitType, TestResult result) {
        this.testTimeStamp = testTimeStamp;
        this.person = person;
        this.station = station;
        this.testBay = testBay;
        this.testKitType = testKitType;
        this.result = result;
    }
}
