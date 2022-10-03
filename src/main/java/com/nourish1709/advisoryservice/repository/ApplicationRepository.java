package com.nourish1709.advisoryservice.repository;

import com.nourish1709.advisoryservice.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
