package com.learn.spring.springboot.service;

import com.learn.spring.springboot.dto.UserDto;
import com.learn.spring.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
