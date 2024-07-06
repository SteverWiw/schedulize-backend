package com.schedulize.backend.domain.services;

import com.schedulize.backend.adapters.infrastructure.repository.MenuRepository;
import com.schedulize.backend.adapters.userinterfaces.presenters.MenuPresenter;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.application.usecases.IMenuService;
import com.schedulize.backend.domain.entities.MenuEntity;
import com.schedulize.backend.util.CustomErrorCode;
import com.schedulize.backend.util.GeneralUtils;
import com.schedulize.backend.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class MenuServiceImpl implements IMenuService {
    private final MenuRepository menuRepository;
    private final ResponseUtil responseUtil = new ResponseUtil();

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

    private MenuEntity saveMenu(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }

    @Override
    public ResponseEntity<ResponseRestPresenter<List<MenuPresenter>>> getParents() {
        List<MenuEntity> menuEntities = menuRepository.getMenuEntityByIsParent("S");

        return Optional.ofNullable(menuEntities)
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(MenuPresenter::fromEntity).toList())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);

    }

    @Override
    public ResponseEntity<ResponseRestPresenter<List<MenuPresenter>>> getChildren(Long parentId) {
        List<MenuEntity> menuEntities = menuRepository.getMenuEntityByParentId(parentId);

        return Optional.ofNullable(menuEntities)
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(MenuPresenter::fromEntity).toList())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }

    public ResponseEntity<ResponseRestPresenter<MenuPresenter>> create(MenuEntity menuEntity) {
        MenuEntity cmenuEntity = validateIsParent(menuEntity);

        if (Objects.nonNull(cmenuEntity)) {
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.CONFLICT.getMessage(), CustomErrorCode.CONFLICT.getCode(), CustomErrorCode.CONFLICT.getHttpCode());
        }
        return Optional.of(saveMenu(menuEntity))
                .map(MenuPresenter::fromEntity)
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }

    @Override
    public ResponseEntity<ResponseRestPresenter<MenuPresenter>> update(MenuEntity menuEntity) {
       return create(menuEntity);
    }

    private MenuEntity validateIsParent(MenuEntity menuEntity) {
        if ("N".equals(menuEntity.getIsParent())) {
            return menuRepository.getMenuEntityByIdViewAndParentId(menuEntity.getIdView(), menuEntity.getParentId());
        }
        return null;
    }
}
