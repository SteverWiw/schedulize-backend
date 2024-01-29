package com.ajsoftware.backendjwtauth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicRequest {

    @JsonProperty(value = "apiName")
    String apiName;

    @JsonProperty(value = "roleName")
    String roleName;

    @JsonProperty(value = "objectrequest")
    List<Map<String, Object>> objectRequest;


}
