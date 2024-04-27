package com.schedulize.backend.adapters.userinterfaces.controller;

import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.application.usecases.IMenuService;
import com.schedulize.backend.domain.entities.MenuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/app-schedule/v1/api${application.request.mappings.menu}")
public class MenuController {

    private final IMenuService iMenuService;


    @GetMapping("/get")
    public ResponseEntity<ResponseRestPresenter<String>>getMenu(){
        return iMenuService.getMenu();
    }

    @GetMapping("/getparents")
    public ResponseEntity<ResponseRestPresenter<List<MenuEntity>>> getParents(){
        return  iMenuService.getParents();
    }

    @GetMapping("/getchildren")
    public ResponseEntity<ResponseRestPresenter<List<MenuEntity>>>getChildren(){
        return iMenuService.getChildren(1L);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseRestPresenter<String>>prueba(){
        return iMenuService.prueba();
    }
}
