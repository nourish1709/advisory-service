package com.nourish1709.advisoryservice.service;

import com.nourish1709.advisoryservice.domain.application.Application;

public interface ApplicationService {

    Application assignApplication(Long advisorId);
}
