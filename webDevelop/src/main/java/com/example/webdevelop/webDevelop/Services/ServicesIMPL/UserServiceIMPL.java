package com.example.webdevelop.webDevelop.Services.ServicesIMPL;

import com.example.webdevelop.webDevelop.Controllers.views.UserViewModel;
import com.example.webdevelop.webDevelop.DTO.UserDTO;
import com.example.webdevelop.webDevelop.Models.User;
import com.example.webdevelop.webDevelop.Repositories.UserRepository;
import com.example.webdevelop.webDevelop.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceIMPL(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDTO.class);
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDTO.getUsername()); //
            User updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser, UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> getUserByUsername(String username) {
        Optional<User> users = userRepository.findUserByUsername(username);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
    @Override
    public List<Object[]> findInactiveUsersWithModels() {
        return userRepository.findInactiveUsersWithModels();
    }


    @Override
    public UserDTO regUserAndCheckExisting(UserViewModel userViewModel) {
        Optional<User> existingUser = userRepository.findUserByUsername(userViewModel.getUsername());
        if (existingUser.isPresent()) {
            return null;
        }
        User newUser = new User();
        newUser.setUsername(userViewModel.getUsername());
        newUser.setPassword(userViewModel.getPassword());
        newUser.setFirstName(userViewModel.getFirstName());
        newUser.setLastName(userViewModel.getLastName());
        newUser.setImageUrl(userViewModel.getImageUrl());
        newUser.setActive(true);


        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }
}




