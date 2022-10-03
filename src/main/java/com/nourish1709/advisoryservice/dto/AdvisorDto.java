package com.nourish1709.advisoryservice.dto;

import com.nourish1709.advisoryservice.domain.system_user.advisor.AdvisoryRole;

public record AdvisorDto(
         String email, String username, String firstName, String lastName, AdvisoryRole role) {
}
