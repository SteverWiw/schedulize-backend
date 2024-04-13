package com.schedulize.backend.util;

import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
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

    public <T> ResponseEntity<ResponseRestPresenter<T>> createResponse(T object) {
        List<T> objects = Collections.singletonList(object);
        return createResponseInternal(objects);
    }
    public <T> ResponseEntity<ResponseRestPresenter<T>> createResponseForAll(List<T> objects) {
        return createResponseInternal(objects);
    }
    private <T> ResponseEntity<ResponseRestPresenter<T>> createResponseInternal(List<T> objects) {
        ResponseRestPresenter<T> response = new ResponseRestPresenter<>();
        response.setObject(objects);
        response.setMetaData(CustomErrorCode.SUCCESS.getMessage(), CustomErrorCode.SUCCESS.getCode(), String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, CustomErrorCode.SUCCESS.getHttpCode());
    }
    public <T> ResponseEntity<ResponseRestPresenter<T>> handleErrorInternalResponse() {
        ResponseRestPresenter<T> response = new ResponseRestPresenter<>();
        response.setMetaData(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage(), CustomErrorCode.INTERNAL_SERVER_ERROR.getCode(),  String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, CustomErrorCode.INTERNAL_SERVER_ERROR.getHttpCode());
    }
    public <T> ResponseEntity<ResponseRestPresenter<T>> handleErrorResponseGeneric(String message, int code, HttpStatus httpStatus) {
        ResponseRestPresenter<T> response = new ResponseRestPresenter<>();
        response.setMetaData(message, code, String.valueOf(LocalDate.now()));
        return new ResponseEntity<>(response, httpStatus);
    }


}

