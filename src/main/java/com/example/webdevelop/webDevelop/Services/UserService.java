package com.example.webdevelop.webDevelop.Services;

import com.example.webdevelop.webDevelop.Controllers.views.UserViewModel;
import com.example.webdevelop.webDevelop.DTO.UserDTO;
import com.example.webdevelop.webDevelop.Models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(UUID id);
    UserDTO updateUser(UUID id,  UserDTO userDTO);
    List<UserDTO> getAllUsers();

    void deleteUser(UUID id);

    List<Object[]> findInactiveUsersWithModels();
    List<UserDTO> getUserByUsername(String username);

    UserDTO regUserAndCheckExisting(UserViewModel userViewModel);
}
