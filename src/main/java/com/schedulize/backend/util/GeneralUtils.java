package com.schedulize.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.util.Base64;
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

}
