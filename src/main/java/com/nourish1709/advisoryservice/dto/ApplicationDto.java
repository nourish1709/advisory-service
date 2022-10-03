package com.nourish1709.advisoryservice.dto;

import com.nourish1709.advisoryservice.domain.application.ApplicationStatus;

import java.math.BigDecimal;

public record ApplicationDto(
         AdvisorDto advisor, ApplicantDto applicant, BigDecimal amountIfMoney, ApplicationStatus status) {
}
