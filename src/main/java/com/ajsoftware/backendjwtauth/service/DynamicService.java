package com.ajsoftware.backendjwtauth.service;

import com.ajsoftware.backendjwtauth.api.DynamicServiceCall;
import com.ajsoftware.backendjwtauth.dto.request.DynamicRequest;
import com.ajsoftware.backendjwtauth.interfaces.IDynamicService;
import com.ajsoftware.backendjwtauth.model.ApiInfoEntity;
import com.ajsoftware.backendjwtauth.repository.ApiInfoRepository;
import com.ajsoftware.backendjwtauth.response.ResponseRest;
import com.ajsoftware.backendjwtauth.util.CustomErrorCode;
import com.ajsoftware.backendjwtauth.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DynamicService implements IDynamicService {

    private final ResponseUtil responseUtil = new ResponseUtil();
    private final ObjectMapper objectMapper;
    private final ApiInfoRepository apiInfoRepository;
    private final DynamicServiceCall dynamicServiceCall;

    @Override
    public <T> ResponseEntity<ResponseRest<T>> dynamicRequest(DynamicRequest dynamicRequest) {
        Optional<ApiInfoEntity> apiInfoOptional = apiInfoRepository.findByApiNameWithApiRole(dynamicRequest.getApiName(), dynamicRequest.getRoleName());

        if (apiInfoOptional.isEmpty()) {
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.NOT_FOUND.getMessage(),
                    CustomErrorCode.NOT_FOUND.getCode(),
                    CustomErrorCode.NOT_FOUND.getHttpCode());
        }
        ApiInfoEntity apiInfoEntity = apiInfoOptional.get();
        return processRequest(apiInfoEntity);
    }

    private <T> ResponseEntity<ResponseRest<T>> processRequest(ApiInfoEntity apiInfoEntity) {
        try {
            ResponseBody jsonString = dynamicServiceCall.get(apiInfoEntity.getSource());
            T data = parseResponse(jsonString);
            return Optional.ofNullable(data)
                    .map(responseUtil::createResponse)
                    .orElseGet(() -> responseUtil.handleErrorResponseGeneric(CustomErrorCode.BAD_REQUEST.getMessage(),
                            CustomErrorCode.BAD_REQUEST.getCode(),
                            CustomErrorCode.BAD_REQUEST.getHttpCode()));
        } catch (JsonProcessingException e) {
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.JSON_PARSE.getMessage(),
                    CustomErrorCode.JSON_PARSE.getCode(),
                    CustomErrorCode.JSON_PARSE.getHttpCode());
        } catch (IOException e) {
            return responseUtil.handleErrorInternalResponse();
        }
    }

    private <T> T parseResponse(ResponseBody jsonString) throws IOException {
        return objectMapper.readValue(jsonString.string(), new TypeReference<>() {});
    }
}
