package com.ajsoftware.backendjwtauth.service;

import com.ajsoftware.backendjwtauth.dto.request.DynamicRequest;
import com.ajsoftware.backendjwtauth.interfaces.DynamicService;
import com.ajsoftware.backendjwtauth.model.ApiInfoEntity;
import com.ajsoftware.backendjwtauth.repository.ApiInfoRepository;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import com.ajsoftware.backendjwtauth.util.CustomErrorCode;
import com.ajsoftware.backendjwtauth.util.ResponseUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DinamicService implements DynamicService {

    private final ResponseUtil responseUtil = new ResponseUtil();
    private final ObjectMapper objectMapper;
    private final ApiInfoRepository apiInfoRepository;

    @Override
    public <T> ResponseEntity<ResponseRest<T>> getInfo(DynamicRequest dynamicRequest) {
        Optional<ApiInfoEntity> apiInfoOptional = apiInfoRepository.findByApiNameWithApiRole(dynamicRequest.getApiName(), dynamicRequest.getRoleName());
        if (apiInfoOptional.isPresent()) {
            ApiInfoEntity apiInfoEntity = apiInfoOptional.get();
            String jsonString = apiInfoEntity.getRequired();
            try {
                T data = objectMapper.readValue(jsonString, new TypeReference<>() {
                });
                assert data != null;
                return Optional.of(data)
                        .map(responseUtil::createResponse)
                        .orElseGet(() -> responseUtil.handleErrorResponseGeneric(CustomErrorCode.BAD_REQUEST.getMessage(),
                                CustomErrorCode.BAD_REQUEST.getCode(),
                                CustomErrorCode.BAD_REQUEST.getHttpCode()));
            } catch (Exception e) {
                return responseUtil.handleErrorInternalResponse();
            }
        } else {
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.NOT_FOUND.getMessage(),
                    CustomErrorCode.NOT_FOUND.getCode(),
                    CustomErrorCode.NOT_FOUND.getHttpCode());
        }
    }
}
