package at.spengergasse.ft2021pos1.part1.persistence;

import at.spengergasse.ft2021pos1.part1.domain.Test;
import at.spengergasse.ft2021pos1.part1.domain.TestKitType;
import at.spengergasse.ft2021pos1.part1.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    long countDistinctByTestKitTypeAndResultAndTestTimeStampBetween(TestKitType testKitType, TestResult result, LocalDateTime testTimeStampStart, LocalDateTime testTimeStampEnd);


}
