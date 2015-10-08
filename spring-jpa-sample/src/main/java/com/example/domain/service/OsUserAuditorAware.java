package com.example.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;

public class OsUserAuditorAware implements AuditorAware<String> {

    @Value("#{ systemProperties['user.name'] }")
    private String userName;
    
    @Override
    public String getCurrentAuditor() {
        return userName;
    }
}
