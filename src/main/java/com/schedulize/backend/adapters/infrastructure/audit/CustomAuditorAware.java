package com.schedulize.backend.adapters.infrastructure.audit;

import com.schedulize.backend.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Slf4j
public class CustomAuditorAware implements AuditorAware<String> {
   @Override
    public Optional<String> getCurrentAuditor() {
        String username = GeneralUtils.getAuthenticationPrincipal();
        return Optional.ofNullable(GeneralUtils.extractUserName(username));
    }
}

