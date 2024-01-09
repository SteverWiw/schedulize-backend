package com.ajsoftware.backendjwtauth.controller.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${application.request.mappings}/v1/api/demo")
@RequiredArgsConstructor
public class DemoController {
    @PostMapping("/demo")
    public String welcome() {
        return "registrer from public endpoitn";
    }
}
