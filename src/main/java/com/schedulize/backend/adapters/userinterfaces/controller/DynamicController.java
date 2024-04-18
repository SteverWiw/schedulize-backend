package com.schedulize.backend.adapters.userinterfaces.controller;


import com.schedulize.backend.application.model.request.DynamicRequestDto;
import com.schedulize.backend.application.usecases.IDynamicService;
import com.schedulize.backend.adapters.userinterfaces.presenters.ResponseRestPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/dynamic")
@RequiredArgsConstructor
public class DynamicController {

    private  final IDynamicService dynamicService;


    @PostMapping("/dynamic")
    public <T> ResponseEntity<ResponseRestPresenter<T>> dynamicRequest(@RequestHeader("Authorization") String token,
                                                                       @RequestBody DynamicRequestDto dynamicRequestDto)  {
        return dynamicService.dynamicRequest(dynamicRequestDto);
    }
}
