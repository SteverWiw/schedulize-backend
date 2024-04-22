package com.schedulize.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class GeneralUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Decodes the provided Base64 encoded string and returns a ByteArrayResource.
     *
     * @param contentBase64 The Base64 encoded string to decode.
     * @return A ByteArrayResource containing the decoded bytes.
     */
    public ByteArrayResource decodeBase64(String contentBase64) {
        byte[] decodedBytes = Base64.getDecoder().decode(contentBase64);
        return new ByteArrayResource(decodedBytes);
    }

    public static <T> T parseJson(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, clazz);
    }

    public static String extractUserName(String input) {
        Pattern pattern = Pattern.compile("userName=([^,]*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String extractRoleId(String input) {
        Pattern pattern = Pattern.compile("RoleEntity\\(id=([^,]*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String getAuthenticationPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return authentication.getPrincipal().toString();
    }

    public static String convertMapToJson(List<Map<String, Object>> menu) {
        try {
            return objectMapper.writeValueAsString(menu);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir el menú a JSON", e);
        }
    }

    public static Boolean isJson(String str) {
        try {
            new ObjectMapper().readTree(str);
            return true;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
