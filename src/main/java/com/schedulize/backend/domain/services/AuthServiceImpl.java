package com.schedulize.backend.domain.services;

import com.schedulize.backend.application.model.request.LoginRequestDto;
import com.schedulize.backend.application.model.request.RegisterRequestDto;
import com.schedulize.backend.application.model.response.AuthResponseDto;
import com.schedulize.backend.application.usecases.AuthService;
import com.schedulize.backend.adapters.infrastructure.jwt.JwtService;
import com.schedulize.backend.domain.entities.UserEntity;
import com.schedulize.backend.adapters.infrastructure.repository.UserRepository;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import com.schedulize.backend.util.CustomErrorCode;
import com.schedulize.backend.util.ResponseUtil;
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
    public ResponseEntity<ResponseRestPresenter<AuthResponseDto>>login(LoginRequestDto loginRequestDto) {
        try {
            UserEntity userDetails = userRepository.findByUserName(loginRequestDto.getUserName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));

           AuthResponseDto authResponseDto = AuthResponseDto.builder().token(jwtService.getToken(userDetails)).build();

            return Optional.of(authResponseDto)
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
    public ResponseEntity<ResponseRestPresenter<String>> register(RegisterRequestDto registerRequestDto) {
        UserEntity user = UserEntity.builder()
                .userName(registerRequestDto.getUserName())
                .status(registerRequestDto.getStatus())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .idRole(registerRequestDto.getIdRole())
                .idPerson(registerRequestDto.getIdPerson())
                .build();

        userRepository.save(user);
        String messaje = "Exito: se realizó el registro de usuario por favor, validar los datos en el e-mail.";
        return Optional.of(messaje)
                .map(responseUtil::createResponse)
                .orElseGet(responseUtil::handleErrorInternalResponse);
    }
}
