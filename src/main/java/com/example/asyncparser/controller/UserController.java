package com.example.asyncparser.controller;

import com.example.asyncparser.dto.UserResponseDto;
import com.example.asyncparser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public List<UserResponseDto> getUsers(@RequestBody String data){
        System.out.println(data);
        return new ArrayList<>();
    }
}
