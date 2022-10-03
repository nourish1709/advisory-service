package com.nourish1709.advisoryservice.domain.system_user.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneNumber {

    private String value;

    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;
}
