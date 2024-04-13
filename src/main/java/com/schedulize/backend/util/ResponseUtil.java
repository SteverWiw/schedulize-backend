package com.schedulize.backend.util;

import com.schedulize.backend.application.model.response.ResponseRestDto;
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

    public <T> ResponseEntity<ResponseRestDto<T>> createResponse(T object) {
        List<T> objects = Collections.singletonList(object);
        return createResponseInternal(objects);
    }
    public <T> ResponseEntity<ResponseRestDto<T>> createResponseForAll(List<T> objects) {
        return createResponseInternal(objects);
    }
    private <T> ResponseEntity<ResponseRestDto<T>> createResponseInternal(List<T> objects) {
        ResponseRestDto<T> response = new ResponseRestDto<>();
        response.setObject(objects);
        response.setMetaData(CustomErrorCode.SUCCESS.getMessage(), CustomErrorCode.SUCCESS.getCode(), String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, CustomErrorCode.SUCCESS.getHttpCode());
    }
    public <T> ResponseEntity<ResponseRestDto<T>> handleErrorInternalResponse() {
        ResponseRestDto<T> response = new ResponseRestDto<>();
        response.setMetaData(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage(), CustomErrorCode.INTERNAL_SERVER_ERROR.getCode(),  String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, CustomErrorCode.INTERNAL_SERVER_ERROR.getHttpCode());
    }
    public <T> ResponseEntity<ResponseRestDto<T>> handleErrorResponseGeneric(String message, int code, HttpStatus httpStatus) {
        ResponseRestDto<T> response = new ResponseRestDto<>();
        response.setMetaData(message, code, String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, httpStatus);
    }


}

