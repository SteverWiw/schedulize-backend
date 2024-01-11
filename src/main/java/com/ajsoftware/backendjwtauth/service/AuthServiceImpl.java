package com.ajsoftware.backendjwtauth.service;

import com.ajsoftware.backendjwtauth.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauth.dto.request.RegisterRequestDto;
import com.ajsoftware.backendjwtauth.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauth.interfaces.AuthService;
import com.ajsoftware.backendjwtauth.jwt.JwtService;
import com.ajsoftware.backendjwtauth.model.User;
import com.ajsoftware.backendjwtauth.repository.UserRepository;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import com.ajsoftware.backendjwtauth.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final ResponseUtil responseUtil = new ResponseUtil();
    @Override
    public ResponseEntity<ResponseRest<AuthResponse>> login(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));
            User userDetails = userRepository.findByUserNameAndStatus(loginRequestDto.getUserName(),"S").orElseThrow(() -> new UsernameNotFoundException("User not found"));
            AuthResponse authResponse = AuthResponse.builder().token(jwtService.getToken(userDetails)).build();
            return Optional.of(authResponse)
                    .filter(authRes -> !authRes.toString().isEmpty())
                    .map(responseUtil::createResponse)
                    .orElseGet(responseUtil::handleResponseNotFound);
        } catch (AuthenticationException e) {
            return responseUtil.handleResponseNotAuthorized();
        }
    }

    @Override
    public ResponseEntity<ResponseRest<AuthResponse>> register(RegisterRequestDto registerRequestDto) {
        User user = User.builder()
                .userName(registerRequestDto.getUserName())
                .status(registerRequestDto.getStatus())
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(registerRequestDto.getRole())
                .build();

        userRepository.save(user);
        AuthResponse authResponse = AuthResponse.builder().token(jwtService.getToken(user)).build();
        return Optional.of(authResponse)
                .filter(authRes -> !authRes.toString().isEmpty())
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleResponseNotFound);
    }
}
