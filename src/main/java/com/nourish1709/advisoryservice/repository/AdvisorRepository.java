package com.nourish1709.advisoryservice.repository;

import com.nourish1709.advisoryservice.domain.system_user.advisor.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}
