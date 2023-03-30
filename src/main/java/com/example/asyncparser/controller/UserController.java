package com.example.asyncparser.controller;

import com.example.asyncparser.dto.UserGeoDataDTO;
import com.example.asyncparser.dto.UserResponseDTO;
import com.example.asyncparser.service.UserGeoService;
import com.example.asyncparser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGeoService userGeoService;

    public static boolean ipIsValid(String ip) {
        String[] split = ip.split("[.]");
        if (split.length != 4) return false;
        try {
            for (String s : split) {
                int value = Integer.parseInt(s);
                if (value < 0 || value > 255) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @PostMapping("/users")
    public List<UserResponseDTO> getUsers(@RequestBody String data) {
        List<String> names = Arrays.stream(data.split("[\\n\\r]+")) //Remove all possible \r\n characters
                .map(x -> x.replaceAll("[ ]", "+")) //For name i.e. "John Smith"
                .collect(Collectors.toList());
        return userService.getUsers(names);
    }

    @GetMapping("/2ip")
    public UserGeoDataDTO getUserGeoData(@RequestParam(required = false) String ip) {
        if (ip == null) {
            ip = "";
        } else {
            if (!ipIsValid(ip)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Ip=%s is invalid", ip));
            }
        }
        return userGeoService.getUserGeoData(ip);
    }
}
