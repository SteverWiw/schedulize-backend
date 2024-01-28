package com.ajsoftware.backendjwtauth.interfaces;

import com.ajsoftware.backendjwtauth.dto.request.DynamicRequest;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import org.springframework.http.ResponseEntity;

public interface DynamicService {

    <T> ResponseEntity<ResponseRest<T>> getInfo(DynamicRequest dynamicRequest);
}
