package com.ajsoftware.backendjwtauth.controller.dynamic;


import com.ajsoftware.backendjwtauth.dto.request.DynamicRequest;
import com.ajsoftware.backendjwtauth.interfaces.IDynamicService;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}/v1/api/dynamic")
@RequiredArgsConstructor
public class DynamicController {

    private  final IDynamicService dynamicService;


    @PostMapping("/dynamic")
    public <T> ResponseEntity<ResponseRest<T>> dynamicRequest(@RequestHeader("Authorization") String token,
                                                              @RequestBody DynamicRequest dynamicRequest)  {
        return dynamicService.dynamicRequest(dynamicRequest);
    }
}
