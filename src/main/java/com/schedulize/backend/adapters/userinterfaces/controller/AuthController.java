package com.schedulize.backend.adapters.userinterfaces.controller;

import com.schedulize.backend.application.model.request.LoginRequestDto;
import com.schedulize.backend.application.model.response.AuthResponseDto;
import com.schedulize.backend.application.usecases.IAuthService;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${application.request.mappings.auth}")
public class AuthController {
    private final IAuthService iAuthService;

    @PostMapping("/login")
    public ResponseEntity<ResponseRestPresenter<AuthResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Ingrese a login");
        return iAuthService.login(loginRequestDto);
    }
}
