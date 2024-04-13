package com.schedulize.backend.adapters.userinterfaces.controller;

import com.schedulize.backend.application.model.request.RegisterRequestDto;
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
        return "register from public endpoint";
    }


    @PostMapping("/registrer")
    public String registrer(@RequestBody RegisterRequestDto registerRequestDto) {
        return "registrer from public endpoitn";
    }
}
