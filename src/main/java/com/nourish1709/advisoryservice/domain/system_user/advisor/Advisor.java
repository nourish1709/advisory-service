package com.nourish1709.advisoryservice.domain.system_user.advisor;

import com.nourish1709.advisoryservice.domain.application.Application;
import com.nourish1709.advisoryservice.domain.application.ApplicationStatus;
import com.nourish1709.advisoryservice.domain.system_user.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advisor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Advisor extends SystemUser {

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private AdvisoryRole role;

    @OneToMany(mappedBy = "advisor")
    private List<Application> applications = new ArrayList<>();

    public void assignApplication(Application oldestNewApplication) {
        this.applications.add(oldestNewApplication);
        oldestNewApplication.setAdvisor(this);
        oldestNewApplication.setAssignedAt(LocalDateTime.now());
        oldestNewApplication.setStatus(ApplicationStatus.ASSIGNED);
    }
}
