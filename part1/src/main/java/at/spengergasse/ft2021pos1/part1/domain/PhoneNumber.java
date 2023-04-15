package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class PhoneNumber {

    private Integer countryCode;
    private Integer areaCode;
    private Integer serialNumber;

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "countryCode=" + countryCode +
                ", areaCode=" + areaCode +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
