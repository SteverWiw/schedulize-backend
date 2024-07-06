package com.schedulize.backend.application.usecases;

import com.schedulize.backend.adapters.userinterfaces.presenters.MenuPresenter;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.domain.entities.MenuEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMenuService {
    ResponseEntity<ResponseRestPresenter<String>> getMenu();
    ResponseEntity<ResponseRestPresenter<List<MenuPresenter>>> getParents();
    ResponseEntity<ResponseRestPresenter<List<MenuPresenter>>> getChildren(Long parentId);
    ResponseEntity<ResponseRestPresenter<MenuPresenter>> create(MenuEntity menuEntity);
    ResponseEntity<ResponseRestPresenter<MenuPresenter>> update(MenuEntity menuEntity);
}
