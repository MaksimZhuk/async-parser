package com.example.asyncparser.controller;

import com.example.asyncparser.dto.UserGeoDataDTO;
import com.example.asyncparser.dto.UserResponseDTO;
import com.example.asyncparser.service.UserGeoService;
import com.example.asyncparser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGeoService userGeoService;

    @PostMapping("/users")
    public List<UserResponseDTO> getUsers(@RequestBody String data) {
        List<String> names = Arrays.stream(data.split("[\\n\\r]+")) //Remove all possible \r\n characters
                .map(x -> x.replaceAll("[ ]", "+")) //For name i.e. "John Smith"
                .collect(Collectors.toList());
        return userService.getUsers(names);
    }
    @GetMapping("/2ip")
    public UserGeoDataDTO getUserGeoData(@RequestParam(required = false) String ip){
        if (ip == null)
            ip="";

        System.out.println(ip);
        return userGeoService.getUserGeoData(ip);
    }
}
