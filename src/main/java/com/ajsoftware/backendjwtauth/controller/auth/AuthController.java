package com.ajsoftware.backendjwtauth.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("${application.request.mappings}/v1/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login() {
        log.info("Ingres a login");
        return "login from public endpoitn";
    }

    @PostMapping("/registrer")
    public String registrer() {
        return "registrer from public endpoitn";
    }
}
