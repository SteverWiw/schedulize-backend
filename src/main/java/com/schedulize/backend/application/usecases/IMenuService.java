package com.schedulize.backend.application.usecases;

import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import org.springframework.http.ResponseEntity;

public interface IMenuService {
    ResponseEntity<ResponseRestPresenter<String>> getMenu();
}
