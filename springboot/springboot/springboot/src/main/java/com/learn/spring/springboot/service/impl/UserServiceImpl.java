package com.learn.spring.springboot.service.impl;

import com.learn.spring.springboot.dto.UserDto;
import com.learn.spring.springboot.entity.User;
import com.learn.spring.springboot.exception.ResourceNotFoundException;
import com.learn.spring.springboot.repository.UserRepository;
import com.learn.spring.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert DTO to entity
        User saveDUser = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(saveDUser);

        // Convert entity back to DTO
        UserDto savedTodoDto = modelMapper.map(savedUser, UserDto.class);

        return savedTodoDto;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found. Please check"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException(
                "User id " + user.getId() + " not found."));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
