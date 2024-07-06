package com.schedulize.backend.adapters.userinterfaces.controller;

import com.schedulize.backend.adapters.userinterfaces.presenters.MenuPresenter;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.application.usecases.IMenuService;
import com.schedulize.backend.domain.entities.MenuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${application.request.mappings.menu}")
public class MenuController {

    @Autowired
    private final IMenuService iMenuService;


    @GetMapping("/list")
    public ResponseEntity<ResponseRestPresenter<String>>getMenu(){
        return iMenuService.getMenu();
    }

    @GetMapping("/getparents")
    public ResponseEntity<ResponseRestPresenter<List<MenuPresenter>>> getParents(){
        return  iMenuService.getParents();
    }

    @GetMapping("/getchildren")
    public ResponseEntity<ResponseRestPresenter<List<MenuPresenter>>> getChildren(){
        return iMenuService.getChildren(1L);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseRestPresenter<MenuPresenter>> create(@RequestBody MenuEntity menuEntity){
        log.info("Create menu: {}", menuEntity);
        return iMenuService.create(menuEntity);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseRestPresenter<MenuPresenter>> update(@RequestBody MenuEntity menuEntity){
        return iMenuService.update(menuEntity);
    }
}
