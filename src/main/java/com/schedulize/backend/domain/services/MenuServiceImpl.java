package com.schedulize.backend.domain.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedulize.backend.adapters.infrastructure.repository.MenuRepository;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.application.usecases.IMenuService;
import com.schedulize.backend.domain.entities.MenuEntity;
import com.schedulize.backend.util.GeneralUtils;
import com.schedulize.backend.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

        return Optional.ofNullable(GeneralUtils.getAuthenticationPrincipal())
                .map(GeneralUtils::extractRoleId)
                .map(Long::parseLong)
                .map(menuRepository::getMenuByRole)
                .filter(menu -> !menu.isEmpty())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }

    @Override
    public ResponseEntity<ResponseRestPresenter<List<MenuEntity>>> getParents() {
        List<MenuEntity> menuEntities = menuRepository.getMenuEntityByIsParent("S");

        return Optional.ofNullable(menuEntities)
                .filter(list -> !list.isEmpty())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);

    }

    @Override
    public ResponseEntity<ResponseRestPresenter<List<MenuEntity>>> getChildren(Long parentId) {
        List<MenuEntity> menuEntities = menuRepository.getMenuEntityByParentId(parentId);

        return Optional.ofNullable(menuEntities)
                .filter(list -> !list.isEmpty())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }

    @Override
    public ResponseEntity<ResponseRestPresenter<String>> prueba() {
        String menuEntities = """
                {/"mensaje/": /"Esto es una prueba /"
                """;

        return Optional.ofNullable(menuEntities)
                .filter(list -> !list.isEmpty())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }
}
