package com.example.webdevelop.webDevelop.Services;

import com.example.webdevelop.webDevelop.DTO.UserRoleDTO;
import com.example.webdevelop.webDevelop.Enum.Role;
import com.example.webdevelop.webDevelop.Models.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO createUserRole(UserRoleDTO userRoleDTO);
    UserRoleDTO getUserRoleById(UUID id);
    List<UserRoleDTO> getAllUserRoles();
    UserRoleDTO updateUserRole(UUID id,UserRoleDTO userRoleDTO);
    void deleteUserRole(UUID id);
    List<Object[]> countUsersByRole();

    UserRole getByRole(Role role);

 //   Optional<UserRoleDTO> findByRole(UserRole role);
}
