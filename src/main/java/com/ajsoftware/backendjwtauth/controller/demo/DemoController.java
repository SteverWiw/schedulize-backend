package com.ajsoftware.backendjwtauth.controller.demo;

import com.ajsoftware.backendjwtauth.dto.request.RegisterRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("${application.request.mappings}/v1/api/demo")
@RequiredArgsConstructor
public class DemoController {
    @GetMapping("/welcome")
    public Map<String, String> welcome() {
        log.info("Entro a demo ");
        Map<String, String> response = new HashMap<>();
        response.put("message", "register from public endpoint");
        return response;
    }


    @PostMapping("/registrer")
    public String registrer(@RequestBody RegisterRequestDto registerRequestDto) {
        return "registrer from public endpoitn";// authService.register(registerRequestDto);
    }
}
