package com.nourish1709.advisoryservice.service;

import com.nourish1709.advisoryservice.domain.application.Application;
import com.nourish1709.advisoryservice.domain.application.ApplicationStatus;
import com.nourish1709.advisoryservice.domain.system_user.advisor.Advisor;
import com.nourish1709.advisoryservice.repository.AdvisorRepository;
import com.nourish1709.advisoryservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class SimpleApplicationService implements ApplicationService{

    private final AdvisorRepository advisorRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Application assignApplication(Long advisorId) {
        final Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "advisor with id %d was not found".formatted(advisorId)));

        // check if it has one ASSIGNED application
        if (advisor.getApplications().stream()
                .anyMatch(application -> application.getStatus().equals(ApplicationStatus.ASSIGNED))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "advisor with id %d has already an assigned application".formatted(advisorId));
        }

        final Application oldestNewApplication = findOldestNewApplication(advisor);
        advisor.assignApplication(oldestNewApplication);
        return oldestNewApplication;
    }

    private Application findOldestNewApplication(Advisor advisor) {
        return applicationRepository.findAll().stream()
                .filter(this::hasStatusNew)
                .filter(application -> fitsToAdvisor(application, advisor))
                .max(Comparator.comparing(Application::getCreatedAt))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "new application for advisor with id %d and status %s was not found".formatted(advisor.getId(), advisor.getRole())));
    }

    private boolean hasStatusNew(Application application) {
        return application.getStatus().equals(ApplicationStatus.NEW);
    }

    private boolean fitsToAdvisor(Application application, Advisor advisor) {
        switch (advisor.getRole()) {
            case ASSOCIATE -> {
                return application.getAmountOfMoney().compareTo(BigDecimal.valueOf(10_000L)) < 0;
            }
            case PARTNER -> {
                return application.getAmountOfMoney().compareTo(BigDecimal.valueOf(10_000L)) >= 0 &&
                        application.getAmountOfMoney().compareTo(BigDecimal.valueOf(50_000L)) <= 0;
            }
            case SENIOR -> {
                return application.getAmountOfMoney().compareTo(BigDecimal.valueOf(50_000L)) > 0;
            }
            default -> {
                return false;
            }
        }
    }
}
