package com.example.asyncparser.service;

import com.example.asyncparser.dto.UserGeoDataDTO;

public interface UserGeoService {
    public UserGeoDataDTO getUserGeoData(String ip);
}
