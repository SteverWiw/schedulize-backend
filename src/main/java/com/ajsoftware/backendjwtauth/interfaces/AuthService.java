package com.ajsoftware.backendjwtauth.interfaces;

import com.ajsoftware.backendjwtauth.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauth.dto.request.RegisterRequestDto;
import com.ajsoftware.backendjwtauth.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseRest<AuthResponse>> login(LoginRequestDto loginRequestDto);
    ResponseEntity<ResponseRest<AuthResponse>> register(RegisterRequestDto registerRequestDto);
}
