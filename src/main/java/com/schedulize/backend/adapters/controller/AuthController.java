package com.schedulize.backend.adapters.controller;

import com.schedulize.backend.application.model.request.LoginRequestDto;
import com.schedulize.backend.application.model.request.RegisterRequestDto;
import com.schedulize.backend.application.model.response.AuthResponseDto;
import com.schedulize.backend.application.usecases.AuthService;
import com.schedulize.backend.application.model.response.ResponseRestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.request.mappings}/v1/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseRestDto<AuthResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Ingrese a login");
        return authService.login(loginRequestDto);
    }

    @PostMapping("/registrer")
    public ResponseEntity<ResponseRestDto<String>> registrer(@RequestBody RegisterRequestDto registerRequestDto) {
        return  authService.register(registerRequestDto);
    }
}
