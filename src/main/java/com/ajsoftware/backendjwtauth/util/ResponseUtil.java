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
        response.setMetaData(CustomErrorCode.SUCCESS.getMessage(), CustomErrorCode.SUCCESS.getCode(), String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, CustomErrorCode.SUCCESS.getHttpCode());
    }
    public <T> ResponseEntity<ResponseRest<T>> handleErrorInternalResponse() {
        ResponseRest<T> response = new ResponseRest<>();
        response.setMetaData(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage(), CustomErrorCode.INTERNAL_SERVER_ERROR.getCode(),  String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, CustomErrorCode.INTERNAL_SERVER_ERROR.getHttpCode());
    }
    public <T> ResponseEntity<ResponseRest<T>> handleErrorResponseGeneric(String message, int code, HttpStatus httpStatus) {
        ResponseRest<T> response = new ResponseRest<>();
        response.setMetaData(message, code, String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, httpStatus);
    }


}

