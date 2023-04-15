package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@Entity
@Table(name = "tests")
public class Test extends AbstractPersistable<Long> {

    @Version
    private Integer version;

    @NotNull
    @PastOrPresent
    private LocalDateTime testTimeStamp;

    @ManyToOne
    private Person person;

    @ManyToOne
    private TestStation station;

    private Integer testBay;
    @Enumerated(EnumType.STRING)
    private TestKitType testKitType;
    @Enumerated(EnumType.STRING)
    @Column(name = "test_result")
    private TestResult result;
}
