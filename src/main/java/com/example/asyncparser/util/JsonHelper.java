package com.example.asyncparser.util;

import com.example.asyncparser.dto.UserResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CompletionException;

public class JsonHelper {
    private JsonHelper() {};
    public static UserResponseDto readValueJackson(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(content, new TypeReference<UserResponseDto>(){});
        } catch (IOException e) {
            throw new CompletionException(e);
        }
    }
}
