package com.ajsoftware.backendjwtauth.controller.auth;

import com.ajsoftware.backendjwtauth.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauth.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauth.interfaces.AuthService;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("${application.request.mappings}/v1/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseRest<AuthResponse>> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Ingres a login");
        return authService.login(loginRequestDto);
    }
}
