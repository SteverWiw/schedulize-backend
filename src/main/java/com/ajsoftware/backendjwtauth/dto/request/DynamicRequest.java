package com.ajsoftware.backendjwtauth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicRequest {
    @JsonProperty(value = "nameapi")
    String apiName;

    @JsonProperty(value = "rolename")
    String roleName;

}
