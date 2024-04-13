package com.schedulize.backend.application.usecases;

import com.schedulize.backend.application.model.request.DynamicRequestDto;
import com.schedulize.backend.application.model.response.ResponseRestDto;
import org.springframework.http.ResponseEntity;

public interface IDynamicService {

    <T> ResponseEntity<ResponseRestDto<T>> dynamicRequest(DynamicRequestDto dynamicRequestDto);
}
