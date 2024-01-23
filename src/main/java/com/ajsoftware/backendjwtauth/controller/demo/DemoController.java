package com.ajsoftware.backendjwtauth.controller.demo;

import com.ajsoftware.backendjwtauth.dto.request.RegisterRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/registrer")
    public String registrer(@RequestBody RegisterRequestDto registerRequestDto) {
        return "registrer from public endpoitn";// authService.register(registerRequestDto);
    }
}
