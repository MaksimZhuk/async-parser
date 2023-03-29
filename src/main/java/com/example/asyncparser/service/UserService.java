package com.example.asyncparser.service;

import com.example.asyncparser.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> getUsers(List<String> names);
}
