package com.ajsoftware.backendjwtauth.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.util.Base64;
@Slf4j
@Component
public class GeneralUtils {


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

}
