package com.ajsoftware.backendjwtauth.controller.auth;

import com.ajsoftware.backendjwtauth.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauth.dto.request.RegisterRequestDto;
import com.ajsoftware.backendjwtauth.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauth.interfaces.AuthService;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//TODO: se debe validar el proyecto sin esta funcionalidad
@CrossOrigin(origins = {"/*"})
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("${application.request.mappings}/v1/api/auth")
public class AuthController {
    @Autowired
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ResponseRest<AuthResponse>> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Ingres a login");
        return authService.login(loginRequestDto);
    }

    @PostMapping("/registrer")
    public ResponseEntity<ResponseRest<AuthResponse>> registrer(@RequestBody RegisterRequestDto registerRequestDto) {
        return authService.register(registerRequestDto);
    }
}
