package com.ajsoftware.backendjwtauth.util;

import com.ajsoftware.backendjwtauth.response.ResponseRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Component
public class ResponseUtil {
    @Autowired
    private ObjectMapper objectMapper;

    public <T> ResponseEntity<ResponseRest<T>> createResponse(T object) {
        List<T> objects = Collections.singletonList(object);
        return createResponseInternal(objects);
    }

    public <T> ResponseEntity<ResponseRest<T>> createResponseForAll(List<T> objects) {
        return createResponseInternal(objects);
    }

    private <T> ResponseEntity<ResponseRest<T>> createResponseInternal(List<T> objects) {
        ResponseRest<T> response = new ResponseRest<>();
        response.setObject(objects);
        response.setMetaData("Proceso realizado con éxito", "00", String.valueOf(LocalDate.now()));
        logResponse(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private <T> void logResponse(ResponseRest<T> response) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            log.info("JSON de la respuesta: " + jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> ResponseEntity<ResponseRest<T>> handleResponseNotFound() {
        ResponseRest<T> response = new ResponseRest<>();
        response.setMetaData("Ocurrio un error inesperado","-1", String.valueOf(LocalDate.now()));
        logResponse(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public <T> ResponseEntity<ResponseRest<T>> handleResponseNotAuthorized() {
        ResponseRest<T> response = new ResponseRest<>();
        response.setMetaData("Usuario no autorizado","-1", String.valueOf(LocalDate.now()));
        logResponse(response);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}

