package com.schedulize.backend.application.usecases;

import com.schedulize.backend.application.model.request.LoginRequestDto;
import com.schedulize.backend.application.model.request.RegisterRequestDto;
import com.schedulize.backend.application.model.response.AuthResponseDto;
import com.schedulize.backend.application.model.response.ResponseRestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseRestDto<AuthResponseDto>> login(LoginRequestDto loginRequestDto);
    ResponseEntity<ResponseRestDto<String>> register(RegisterRequestDto registerRequestDto);
}
