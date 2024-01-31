package com.ajsoftware.backendjwtauth.interfaces;

import com.ajsoftware.backendjwtauth.dto.request.DynamicRequest;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import org.springframework.http.ResponseEntity;

public interface IDynamicService {

    <T> ResponseEntity<ResponseRest<T>> dynamicRequest(DynamicRequest dynamicRequest);
}
