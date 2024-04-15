package com.schedulize.backend.domain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedulize.backend.adapters.infrastructure.repository.MenuRepository;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.application.usecases.IMenuService;
import com.schedulize.backend.util.GeneralUtils;
import com.schedulize.backend.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class MenuServiceImpl implements IMenuService {
    private final MenuRepository menuRepository;
    private final ResponseUtil responseUtil = new ResponseUtil();

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ResponseEntity<ResponseRestPresenter<String>> getMenu() {
        String userData  = GeneralUtils.getAuthenticationPrincipal();
        Long roleId = Long.parseLong(Objects.requireNonNull(GeneralUtils.extractRoleId(userData)));

        return Optional.ofNullable(GeneralUtils.getAuthenticationPrincipal())
                .map(GeneralUtils::extractRoleId)
                .map(Long::parseLong)
                .map(menuRepository::getMenuByRole)
                .filter(menu -> !menu.isEmpty())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }
}
