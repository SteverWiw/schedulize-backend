package com.schedulize.backend.application.usecases;

import com.schedulize.backend.application.model.request.DynamicRequestDto;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import org.springframework.http.ResponseEntity;

public interface IDynamicService {

    <T> ResponseEntity<ResponseRestPresenter<T>> dynamicRequest(DynamicRequestDto dynamicRequestDto);
}
