package com.ajsoftware.backendjwtauth.controller.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@Slf4j
@RestController
@RequestMapping("${application.request.mappings}/v1/api/demo")
@RequiredArgsConstructor
public class DemoController {
    @GetMapping("/welcome")
    public String welcome() {
        log.info("Entro a demo ");
        return "registrer from public endpoitn";
    }
}
