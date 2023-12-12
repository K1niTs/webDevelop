package com.example.webdevelop.webDevelop.Services.ServicesIMPL;

import com.example.webdevelop.webDevelop.Controllers.views.RegUserViewModel;
import com.example.webdevelop.webDevelop.Controllers.views.UserViewModel;
import com.example.webdevelop.webDevelop.DTO.UserDTO;
import com.example.webdevelop.webDevelop.Enum.Role;
import com.example.webdevelop.webDevelop.Models.User;
import com.example.webdevelop.webDevelop.Models.UserRole;
import com.example.webdevelop.webDevelop.Repositories.UserRepository;
import com.example.webdevelop.webDevelop.Repositories.UserRoleRepository;
import com.example.webdevelop.webDevelop.Services.UserService;
import com.example.webdevelop.webDevelop.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceIMPL(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, ValidationUtil validationUtil, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> violations = validationUtil.violations(userDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        User user = modelMapper.map(userDTO, User.class);


        if (user.getRole() != null) {

            UserRole savedRole = userRoleRepository.save(user.getRole());

            user.setRole(savedRole);
        }

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
    public User getUserByUsername(String username) {
        Optional<User> users = userRepository.findUserByUsername(username);
        return users.get();
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
   //     newUser.setPassword(userViewModel.getPassword());
        newUser.setFirstName(userViewModel.getFirstName());
        newUser.setLastName(userViewModel.getLastName());
   //     newUser.setImageUrl(userViewModel.getImageUrl());
        newUser.setActive(true);


        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

}




