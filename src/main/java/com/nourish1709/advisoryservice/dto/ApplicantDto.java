package com.nourish1709.advisoryservice.dto;

import com.nourish1709.advisoryservice.domain.system_user.applicant.Address;

public record ApplicantDto(
        String email, String username, String firstName, String lastName, String ssn, Address address) {
}
