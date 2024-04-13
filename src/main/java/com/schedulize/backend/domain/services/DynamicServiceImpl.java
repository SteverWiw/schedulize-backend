package com.schedulize.backend.domain.services;

import com.schedulize.backend.adapters.externalapi.DynamicServiceCall;
import com.schedulize.backend.application.model.request.DynamicRequestDto;
import com.schedulize.backend.application.usecases.IDynamicService;
import com.schedulize.backend.domain.entities.ApiInfoEntity;
import com.schedulize.backend.adapters.repository.ApiInfoRepository;
import com.schedulize.backend.application.model.response.ResponseRestDto;
import com.schedulize.backend.util.CustomErrorCode;
import com.schedulize.backend.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DynamicServiceImpl implements IDynamicService {

    private final ResponseUtil responseUtil = new ResponseUtil();
    private final ObjectMapper objectMapper;
    private final ApiInfoRepository apiInfoRepository;
    private final DynamicServiceCall dynamicServiceCall;
    private final MediaType mediaType =  MediaType.parse("application/json; charset=utf-8");


    @Override
    public <T> ResponseEntity<ResponseRestDto<T>> dynamicRequest(DynamicRequestDto dynamicRequestDto) {
        Optional<ApiInfoEntity> apiInfoOptional = apiInfoRepository.findByApiNameWithApiRole(dynamicRequestDto.getApiName(), dynamicRequestDto.getRoleName());

        if (apiInfoOptional.isEmpty()) {
            return responseUtil.handleErrorResponseGeneric(CustomErrorCode.NOT_FOUND.getMessage(),
                    CustomErrorCode.NOT_FOUND.getCode(),
                    CustomErrorCode.NOT_FOUND.getHttpCode());
        }
        ApiInfoEntity apiInfoEntity = apiInfoOptional.get();
        return processRequest(apiInfoEntity, dynamicRequestDto.getObjectRequest().toString());
    }

    private <T> ResponseEntity<ResponseRestDto<T>> processRequest(ApiInfoEntity apiInfoEntity, String objectRequest) {
        try {
            ResponseBody jsonString;
            RequestBody body;
            String jsonRequestBody;
            if (apiInfoEntity.getMethod().equals("post") || apiInfoEntity.getMethod().equals("put") || apiInfoEntity.getMethod().equals("delete")) {
                jsonRequestBody = parseRequest(objectRequest);
                body = RequestBody.create(jsonRequestBody, mediaType);
                jsonString = dynamicServiceCall.post(apiInfoEntity.getSource(), body);
            } else {
                jsonString = dynamicServiceCall.get(apiInfoEntity.getSource());
            }

            T data = parseResponse(jsonString.toString());
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

    private <T> T parseResponse(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString, new TypeReference<>() {});
    }

    private String parseRequest(String objectRequest) throws IOException {
        return  objectMapper.writeValueAsString(objectRequest);
    }


}
