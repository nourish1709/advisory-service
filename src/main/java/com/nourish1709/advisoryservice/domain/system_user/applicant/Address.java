package com.nourish1709.advisoryservice.domain.system_user.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String number;
    private String zip;
    private String apt;
}
