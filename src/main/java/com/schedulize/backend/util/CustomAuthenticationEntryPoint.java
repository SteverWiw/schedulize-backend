package com.schedulize.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedulize.backend.application.model.response.ResponseRestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ResponseUtil responseUtil;
    public CustomAuthenticationEntryPoint(ResponseUtil responseUtil) {
        this.responseUtil = responseUtil;
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<ResponseRestDto<Void>> responseEntity = responseUtil.handleErrorInternalResponse();
        PrintWriter writer = response.getWriter();
        writer.println(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
    }
}
