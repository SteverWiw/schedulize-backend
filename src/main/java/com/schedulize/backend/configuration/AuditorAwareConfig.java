package com.schedulize.backend.configuration;

import com.schedulize.backend.adapters.infrastructure.auditor.CustomAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new CustomAuditorAware();
    }
}
