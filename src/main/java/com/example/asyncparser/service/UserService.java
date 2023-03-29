package com.example.asyncparser.service;

import com.example.asyncparser.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    public List<UserResponseDTO> getUsers(List<String> names);
}
