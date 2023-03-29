package com.example.asyncparser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserGeoDataDTO {

    private String ip;
    @JsonProperty("country_code")
    private String countryCode;
    private String country;
    @JsonProperty("country_rus")
    private String countryRus;
    @JsonProperty("region_ua")
    private String regionUa;
    @JsonProperty("country_ua")
    private String countryUa;
    private String region;
    @JsonProperty("region_rus")
    private String regionRus;
    private String city;
    @JsonProperty("city_rus")
    private String cityRus;
    private String latitude;
    private String longitude;
    @JsonProperty("zip_code")
    private String zipCode;
    @JsonProperty("time_zone")
    private String timeZone;
}
