package com.schedulize.backend.application.usecases;

import com.schedulize.backend.application.model.request.LoginRequestDto;
import com.schedulize.backend.application.model.request.RegisterRequestDto;
import com.schedulize.backend.application.model.response.AuthResponseDto;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseRestPresenter<AuthResponseDto>> login(LoginRequestDto loginRequestDto);
    ResponseEntity<ResponseRestPresenter<String>> register(RegisterRequestDto registerRequestDto);
}
