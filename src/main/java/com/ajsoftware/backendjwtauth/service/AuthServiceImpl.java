package com.ajsoftware.backendjwtauth.service;

import com.ajsoftware.backendjwtauth.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauth.dto.request.RegisterRequestDto;
import com.ajsoftware.backendjwtauth.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauth.interfaces.AuthService;
import com.ajsoftware.backendjwtauth.jwt.JwtService;
import com.ajsoftware.backendjwtauth.model.UserEntity;
import com.ajsoftware.backendjwtauth.repository.UserRepository;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import com.ajsoftware.backendjwtauth.util.CustomErrorCode;
import com.ajsoftware.backendjwtauth.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
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
    private final ResponseUtil responseUtil = new ResponseUtil();

    @Override
    public ResponseEntity<ResponseRest<AuthResponse>>login(LoginRequestDto loginRequestDto) {
        try {
            UserEntity userDetails = userRepository.findByUserName(loginRequestDto.getUserName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));
            AuthResponse authResponse = AuthResponse.builder().token(jwtService.getToken(userDetails)).build();

            return Optional.of(authResponse)
                    .filter(authRes -> !authRes.toString().isEmpty())
                    .map(responseUtil::createResponse)
                    .orElseGet(responseUtil::handleErrorInternalResponse);
        } catch (UsernameNotFoundException e){
            log.info("El usuario no se encuentra registrado");
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.USER_NOTFOUND.getMessage(), CustomErrorCode.USER_NOTFOUND.getCode(),CustomErrorCode.USER_NOTFOUND.getHttpCode());
        }catch (BadCredentialsException e){
            log.info("Contraseña incorrecta");
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.PASS_INCORRECT.getMessage(), CustomErrorCode.PASS_INCORRECT.getCode(),CustomErrorCode.PASS_INCORRECT.getHttpCode());
        }catch (DisabledException e){
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.USER_BLOCKED.getMessage(),  CustomErrorCode.USER_BLOCKED.getCode(),CustomErrorCode.USER_BLOCKED.getHttpCode());
        }
    }

    @Override
    public ResponseEntity<ResponseRest<String>> register(RegisterRequestDto registerRequestDto) {
        UserEntity user = UserEntity.builder()
                .userName(registerRequestDto.getUserName())
                .status(registerRequestDto.getStatus())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .idRole(registerRequestDto.getIdRole())
                .build();

        userRepository.save(user);
        String messaje = "Exito: se realizó el registro de usuario por favor, validar los datos en el e-mail.";
        return Optional.of(messaje)
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }
}
