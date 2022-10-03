package com.nourish1709.advisoryservice.controller;

import com.nourish1709.advisoryservice.domain.application.Application;
import com.nourish1709.advisoryservice.domain.system_user.advisor.Advisor;
import com.nourish1709.advisoryservice.domain.system_user.applicant.Applicant;
import com.nourish1709.advisoryservice.dto.AdvisorDto;
import com.nourish1709.advisoryservice.dto.ApplicantDto;
import com.nourish1709.advisoryservice.dto.ApplicationDto;
import com.nourish1709.advisoryservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/assign/{advisorId}")
    public ResponseEntity<ApplicationDto> assignApplication(@PathVariable Long advisorId) {
        final Application application = applicationService.assignApplication(advisorId);

        return ResponseEntity.ok(constructApplicationDto(application));
    }

    private ApplicationDto constructApplicationDto(Application application) {
        final Advisor advisor = application.getAdvisor();
        final AdvisorDto advisorDto = new AdvisorDto(advisor.getEmail(), advisor.getUsername(), advisor.getFirstName(), advisor.getLastName(), advisor.getRole());
        final Applicant applicant = application.getApplicant();
        final ApplicantDto applicantDto = new ApplicantDto(applicant.getEmail(), applicant.getUsername(), applicant.getFirstName(), applicant.getLastName(), applicant.getSsn(), applicant.getAddress());

        return new ApplicationDto(advisorDto, applicantDto, application.getAmountOfMoney(), application.getStatus());
    }
}
