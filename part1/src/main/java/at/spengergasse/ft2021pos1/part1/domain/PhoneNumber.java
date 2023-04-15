package at.spengergasse.ft2021pos1.part1.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    private Integer countryCode;
    private Integer areaCode;
    private Integer serialNumber;
}
