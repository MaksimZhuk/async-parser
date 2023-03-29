package com.example.asyncparser.util;

import com.example.asyncparser.dto.UserGeoDataDTO;
import com.example.asyncparser.dto.UserResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CompletionException;

public class JsonHelper {
    private JsonHelper() {};
    public static UserResponseDTO getUserResponseDTO(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(content, new TypeReference<UserResponseDTO>(){});
        } catch (IOException e) {
            throw new CompletionException(e);
        }
    }

    public static UserGeoDataDTO getUserGeoDataDTO(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(content, new TypeReference<UserGeoDataDTO>(){});
        } catch (IOException e) {
            throw new CompletionException(e);
        }
    }

}
