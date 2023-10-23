package com.example.webdevelop.webDevelop.Services;

import com.example.webdevelop.webDevelop.DTO.UserDTO;
import com.example.webdevelop.webDevelop.Models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(UUID id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UUID id,  UserDTO userDTO);
    void deleteUser(UUID id);

    List<Object[]> findInactiveUsersWithModels();

}
