package com.schedulize.backend.application.usecases;

import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.domain.entities.MenuEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMenuService {
    ResponseEntity<ResponseRestPresenter<String>> getMenu();
    ResponseEntity<ResponseRestPresenter<List<MenuEntity>>> getParents();
    ResponseEntity<ResponseRestPresenter<List<MenuEntity>>> getChildren(Long parentId);

    ResponseEntity<ResponseRestPresenter<String>> prueba();
}
