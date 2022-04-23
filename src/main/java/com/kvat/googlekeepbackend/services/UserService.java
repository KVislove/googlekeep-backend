package com.kvat.googlekeepbackend.services;

import com.kvat.googlekeepbackend.dtos.LoginDto;
import com.kvat.googlekeepbackend.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
    UserDto isValidUser(LoginDto loginDto);
}
