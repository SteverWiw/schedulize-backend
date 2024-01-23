package com.ajsoftware.backendjwtauth.util;

import com.ajsoftware.backendjwtauth.response.ResponseRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ResponseUtil {

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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public <T> ResponseEntity<ResponseRest<T>> handleResponseNotFound() {
        ResponseRest<T> response = new ResponseRest<>();
        response.setMetaData("Ocurrio un error inesperado","-1", String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    public <T> ResponseEntity<ResponseRest<T>> handleResponseGeneric(String message,HttpStatus httpStatus) {
        ResponseRest<T> response = new ResponseRest<>();
        response.setMetaData(message, "-1", String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, httpStatus);
    }
}

