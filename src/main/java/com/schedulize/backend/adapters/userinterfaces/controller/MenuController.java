package com.schedulize.backend.adapters.userinterfaces.controller;

import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.application.usecases.IMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${application.request.mappings}/v1/api/menu")
public class MenuController {

    private final IMenuService iMenuService;


    @GetMapping("/get")
    public ResponseEntity<ResponseRestPresenter<String>>getMenu(){
        return iMenuService.getMenu();
    }
}
